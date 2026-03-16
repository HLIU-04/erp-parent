package com.erp.service.Impl;

import com.erp.dto.OrderDetailDTO;
import com.erp.dto.OrderSubmitDTO;
import com.erp.entity.SaleOrder;
import com.erp.entity.SaleOrderDetail;
import com.erp.exception.OrderNotFoundException;
import com.erp.mapper.OrderDetailMapper;
import com.erp.mapper.OrderMapper;
import com.erp.mapper.ProductMapper;
import com.erp.service.OrderService;
import lombok.extern.slf4j.Slf4j;
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
}
