package com.erp.service.Impl;

import com.erp.dto.DailyReportDeliveryDTO;
import com.erp.dto.DailyReportRemainingDTO;
import com.erp.dto.DailyReportUpdateDTO;
import com.erp.entity.DailyReport;
import com.erp.entity.DailyReportItem;
import com.erp.entity.Dept;
import com.erp.entity.Product;
import com.erp.exception.BusinessException;
import com.erp.mapper.DailyReportItemMapper;
import com.erp.mapper.DailyReportMapper;
import com.erp.mapper.DeptMapper;
import com.erp.mapper.ProductMapper;
import com.erp.result.PageResult;
import com.erp.service.DailyReportService;
import com.erp.vo.DailyReportDetailVO;
import com.erp.vo.DailyReportPageVO;
import com.erp.vo.TurnoverStatVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DailyReportServiceImpl implements DailyReportService {

    @Autowired
    private DailyReportMapper dailyReportMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private DailyReportItemMapper dailyReportItemMapper;
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 添加日报发货信息
     * @param dailyReportDeliveryDTO
     */
    @Transactional
    public void delivery(DailyReportDeliveryDTO dailyReportDeliveryDTO) {
        // 获取部门ID
        Integer deptId = dailyReportDeliveryDTO.getDeptId();
        // 获取发货日期
        LocalDate deliveryDate = dailyReportDeliveryDTO.getDeliveryDate();
        // 获取发货项目
        List<DailyReportDeliveryDTO.DeliveryItemDTO> items = dailyReportDeliveryDTO.getItems();

        if (items == null || items.isEmpty()){
            throw new BusinessException("发货明细不能为空");
        }

        // 1. 查找或创建日报主表记录
        DailyReport report = dailyReportMapper.selectByDeptAndDate(deptId, deliveryDate);
        if (report == null){
            report = DailyReport.builder()
                    .deptId(deptId)
                    .deliveryDate(deliveryDate)
                    .totalAmount(BigDecimal.ZERO)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
            dailyReportMapper.insert(report);
            log.info("创建新日报，ID:{}",report.getId());
        }else {
            log.info("日报已存在，ID:{}",report.getId());
        }

        // 2. 准备批量插入或更新的明细
        List<DailyReportItem> itemList = new ArrayList<>();
        for (DailyReportDeliveryDTO.DeliveryItemDTO itemDto : items){
            Integer productId = itemDto.getProductId();
            BigDecimal deliveryWeight = itemDto.getDeliveryWeight();

            //查询商品当前单价
            Product product = productMapper.getById(productId);
            if (product == null){
                throw new BusinessException("商品不存在，ID:" + product);
            }
            BigDecimal unitPrice = product.getPrice();

            DailyReportItem item = DailyReportItem.builder()
                    .reportId(report.getId())
                    .productId(productId)
                    .unitPrice(unitPrice)
                    .deliveryWeight(deliveryWeight)
                    .remainingWeight(BigDecimal.ZERO)
                    .sortOrder(0)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
            itemList.add(item);
        }
        // 3. 执行批量插入或更新
        for (DailyReportItem item : itemList){
            dailyReportItemMapper.insertOrUpdate(item);
        }
        log.info("发货录入成功，日报ID：{}，商品数量：{}", report.getId(), itemList.size());
    }

    /**
     * 添加日报结存信息
     * @param dailyReportRemainingDTO
     */
    @Transactional
    public void remaining(DailyReportRemainingDTO dailyReportRemainingDTO) {
        Integer deptId = dailyReportRemainingDTO.getDeptId();
        LocalDate deliveryDate = dailyReportRemainingDTO.getDeliveryDate();
        List<DailyReportRemainingDTO.RemainingItemDTO> items = dailyReportRemainingDTO.getItems();

        //1.查询日报主表
        DailyReport report = dailyReportMapper.selectByDeptAndDate(deptId, deliveryDate);
        if (report == null){
            throw new BusinessException("未找到该日期的发货记录，请先录入发货");
        }

        //2.查询该日报的所有明细（为了计算营业额）
        List<DailyReportItem> existingItems = dailyReportItemMapper.selectByReportId(report.getId());
        //将明细转为map，方便按productId查询
        Map<Integer, DailyReportItem> itemMap = existingItems.stream()
                .collect(Collectors.toMap(DailyReportItem::getProductId, Function.identity()));

        //3.更新剩货重量
        for (DailyReportRemainingDTO.RemainingItemDTO dto : items){
            Integer productId = dto.getProductId();
            BigDecimal remainingWeight = dto.getRemainingWeight();
            DailyReportItem item = itemMap.get(productId);
            if (item == null){
                throw new BusinessException("商品ID " + productId + " 未在发货记录中找到");
            }
            item.setRemainingWeight(remainingWeight);
            item.setUpdateTime(LocalDateTime.now());
            dailyReportItemMapper.update(item);
        }

        //4.重新计算营业额
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (DailyReportItem item : itemMap.values()){
            BigDecimal weightDiff = item.getDeliveryWeight().subtract(item.getRemainingWeight());
            BigDecimal amount = weightDiff.multiply(item.getUnitPrice());
            totalAmount = totalAmount.add(amount);
        }

        log.info("计算出的总营业额：{}", totalAmount);

        //5.更新主表
        report.setTotalAmount(totalAmount);
        report.setUpdateTime(LocalDateTime.now());
        dailyReportMapper.update(report);
    }

    /**
     * 根据ID查询日报详情
     * @param id
     * @return
     */
    public DailyReportDetailVO getReportDetailById(Integer id) {
        //查询日报主表
        DailyReport report = dailyReportMapper.selectById(id);
        if (report == null) {
            throw new BusinessException("日报不存在");
        }

        //查询日报明细
        List<DailyReportItem> items = dailyReportItemMapper.selectByReportId(report.getId());

        //查询部门名称
        Dept dept = deptMapper.getById(report.getDeptId());
        String deptName = dept != null ? dept.getName() : "未知部门";

        //组装明细vo列表
        List<DailyReportDetailVO.DailyReportItemVO> itemVOList = new ArrayList<>();
        for (DailyReportItem item : items){
            //查询商品名称
            Product product = productMapper.getById(item.getProductId());
            String productName = product != null ? product.getName() : "未知商品";

            //计算发货金额和剩货金额
            BigDecimal deliveryAmount = item.getDeliveryWeight().multiply(item.getUnitPrice());
            BigDecimal remainingAmount = item.getRemainingWeight().multiply(item.getUnitPrice());

            DailyReportDetailVO.DailyReportItemVO itemVO = DailyReportDetailVO.DailyReportItemVO.builder()
                    .productId(item.getProductId())
                    .productName(productName)
                    .unitPrice(item.getUnitPrice())
                    .deliveryWeight(item.getDeliveryWeight())
                    .remainingWeight(item.getRemainingWeight())
                    .deliveryAmount(deliveryAmount)
                    .remainingAmount(remainingAmount)
                    .sortOrder(item.getSortOrder())
                    .build();
            itemVOList.add(itemVO);
        }

        //组装返回结果
        return DailyReportDetailVO.builder()
                .id(report.getId())
                .deliveryDate(report.getDeliveryDate())
                .deptId(report.getDeptId())
                .deptName(deptName)
                .totalAmount(report.getTotalAmount())
                .items(itemVOList)
                .build();
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param deptId
     * @param startDate
     * @param endDate
     * @return
     */
    public PageResult pageQuery(Integer pageNum, Integer pageSize, Integer deptId, LocalDate startDate, LocalDate endDate) {

        // TODO: 后续登录功能完成后，应从当前登录员工获取部门ID，不再依赖前端传入

        //开启分页
        PageHelper.startPage(pageNum, pageSize);

        //查询主表主句
        List<DailyReport> reportList =dailyReportMapper.selectPage(deptId, startDate, endDate);

        //用PageInfo包装，获取分页信息
        PageInfo<DailyReport> pageInfo = new PageInfo<>(reportList);

        //转换为vo列表
        List<DailyReportPageVO> voList = reportList.stream()
                .map(report -> {

                    Dept dept = deptMapper.getById(report.getDeptId());

                    String deptName = (dept != null) ? dept.getName() : "未知部门";

                    return DailyReportPageVO.builder()
                            .id(report.getId())
                            .deliveryDate(report.getDeliveryDate())
                            .deptId(report.getDeptId())
                            .deptName(deptName)
                            .totalAmount(report.getTotalAmount())
                            .build();
                })
                .collect(Collectors.toList());

        // 5. 返回 PageResult（包含 total 和 records）
        return new PageResult(pageInfo.getTotal(), voList);
    }

    /**
     * 修改日报
     * @param id
     * @param dailyReportUpdateDTO
     */
    @Transactional
    public void update(Integer id, DailyReportUpdateDTO dailyReportUpdateDTO) {

        //查询日报是否存在
        DailyReport report = dailyReportMapper.selectById(id);
        if (report == null){
            throw new BusinessException("日报不存在");
        }

        //删除原明细
        dailyReportItemMapper.deleteByReportId(id);

        //插入新明细并重新计算总营业额
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (DailyReportUpdateDTO.DailyReportItemUpdateDTO itemDTO : dailyReportUpdateDTO.getItems()){
            //插入明细
            DailyReportItem item = DailyReportItem.builder()
                    .reportId(id)
                    .productId(itemDTO.getProductId())
                    .unitPrice(itemDTO.getUnitPrice())
                    .deliveryWeight(itemDTO.getDeliveryWeight())
                    .remainingWeight(itemDTO.getRemainingWeight())
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
                    dailyReportItemMapper.insertOrUpdate(item);

                    //计算该商品的营业额
                    BigDecimal turnover = item.getDeliveryWeight()
                        .subtract(item.getRemainingWeight())
                        .multiply(item.getUnitPrice());
                    totalAmount = totalAmount.add(turnover);
        }

        //更新主表营业额
        report.setTotalAmount(totalAmount);
        report.setUpdateTime(LocalDateTime.now());
        dailyReportMapper.update(report);
    }

    /**
     * 根据日期范围查询营业额
     * @param startDate
     * @param endDate
     * @param deptId
     * @return
     */
    public List<TurnoverStatVO> getTurnoverByDateRange(LocalDate startDate, LocalDate endDate, Integer deptId) {

        List<DailyReport> reportList = dailyReportMapper.selectTurnoverByDateRange(startDate, endDate, deptId);
        return reportList.stream()
                .map(report -> TurnoverStatVO.builder()
                        .date(report.getDeliveryDate())
                        .totalAmount(report.getTotalAmount())
                        .build())
                .collect(Collectors.toList());
    }
}
