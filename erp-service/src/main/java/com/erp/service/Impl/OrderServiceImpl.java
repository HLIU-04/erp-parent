package com.erp.service.Impl;

import com.erp.dto.OrderDetailDTO;
import com.erp.dto.OrderSubmitDTO;
import com.erp.entity.*;
import com.erp.exception.OrderNotFoundException;
import com.erp.mapper.*;
import com.erp.service.OrderService;
import com.erp.vo.OrderItemVO;
import com.erp.vo.admin.AdminOrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 根据id删除订单
     * @param id
     */
    @Transactional
    public void deleteById(Integer id) {

        //1.删除订单明细
        orderDetailMapper.deleteByOrderId(id);

        //2.删除订单
        int rows = orderMapper.deleteById(id);

        //3.判断删除结果
        if (rows == 0){
            throw new OrderNotFoundException("订单不存在");
        }

    }

    /**
     * 添加订单信息
     * @param orderSubmitDTO
     */
    @Transactional
    public void submit(OrderSubmitDTO orderSubmitDTO) {

        //1.生成订单号：当前时间 + 2位随机数
        String orderNo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + String.format("%02d",new Random().nextInt(100));

        //2.计算应收总额，并构建明细列表
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<SaleOrderDetail> detailList = new ArrayList<>();

        for(OrderDetailDTO item : orderSubmitDTO.getDetails()) {
            BigDecimal subTotal = item.getQuantity().multiply(item.getUnitPrice());
            totalAmount = totalAmount.add(subTotal);

            // 使用 builder 构建明细对象（此时 orderId 未知，先不设置）
            SaleOrderDetail detail = SaleOrderDetail.builder()
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .unitPrice(item.getUnitPrice())
                    .totalPrice(subTotal)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();
            detailList.add(detail);
        }

        //3.构建主表对象
        SaleOrder order = SaleOrder.builder()
                .orderNo(orderNo)
                .customerId(orderSubmitDTO.getCustomerId())
                .operatorId(orderSubmitDTO.getOperatorId())
                .deptId(orderSubmitDTO.getDeptId())
                .totalAmount(totalAmount)
                .actualAmount(orderSubmitDTO.getActualAmount() != null ? orderSubmitDTO.getActualAmount() : totalAmount)
                .payMethod(orderSubmitDTO.getPayMethod() != null ? orderSubmitDTO.getPayMethod() : "现金")
                .status(1)
                .saleTime(LocalDateTime.now())
                .remark(orderSubmitDTO.getRemark())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        // 4. 插入主表，获取自增ID
        orderMapper.insert(order);
        Integer orderId = order.getId();

        // 5. 为所有明细设置订单ID（builder 无法修改已创建的对象，所以需要重新构建或使用 setter）
        for (SaleOrderDetail detail : detailList) {
            detail.setOrderId(orderId);  // 使用 setter 补充 orderId
        }

        // 6. 批量插入明细
        if (!detailList.isEmpty()) {
            orderDetailMapper.insertBatch(detailList);
        }

        log.info("订单提交成功，订单号：{}，订单ID：{}", orderNo, orderId);
    }

    /**
     * 根据id查询订单信息
     * @param id
     */
    public AdminOrderDetailVO getAdminOrderDetailById(Integer id) {
        // 1. 查询订单主表
        SaleOrder order = orderMapper.getById(id);
        if (order == null) {
            throw new OrderNotFoundException("订单不存在，id: " + id);
        }

        // 2. 查询明细列表（含商品名称）
        List<OrderItemVO> items = orderDetailMapper.selectItemsByOrderId(id);

        // 3. 组装 VO
        AdminOrderDetailVO vo = new AdminOrderDetailVO();
        // 复制同名属性（id, orderNo, customerId, operatorId, deptId, totalAmount, actualAmount, payMethod, status, saleTime, remark）
        BeanUtils.copyProperties(order, vo);
        vo.setItems(items);

        //补充客户姓名
        if (order.getCustomerId() != null) {
             Customer customer = customerMapper.getById(order.getCustomerId());
             if (customer != null) vo.setCustomerName(customer.getName());
        }

        //补充员工姓名
        if (order.getOperatorId() != null) {
            Employee employee = employeeMapper.getById(order.getOperatorId());
            if (employee != null) vo.setOperatorName(employee.getName());
        }

        //补充部门名称
        if (order.getDeptId() != null) {
            Dept dept = deptMapper.getById(order.getDeptId());
            if (dept != null) vo.setDeptName(dept.getName());
        }

        return vo;
    }
}
