package com.erp.service.Impl;

import com.erp.dto.DailyReportDeliveryDTO;
import com.erp.entity.DailyReport;
import com.erp.entity.DailyReportItem;
import com.erp.entity.Product;
import com.erp.exception.BusinessException;
import com.erp.mapper.DailyReportItemMapper;
import com.erp.mapper.DailyReportMapper;
import com.erp.mapper.ProductMapper;
import com.erp.service.DailyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DailyReportServiceImpl implements DailyReportService {

    @Autowired
    private DailyReportMapper dailyReportMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private DailyReportItemMapper dailyReportItemMapper;

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
}
