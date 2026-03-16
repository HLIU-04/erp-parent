package com.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单提交时接收前端数据的DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSubmitDTO {

    private Integer customerId;// 客户ID（散客可为空）

    private Integer operatorId;// 操作员工ID

    private Integer deptId;// 部门（摊位）ID

    private String payMethod;// 支付方式

    private BigDecimal actualAmount;// 实收金额（可选，如果不传则等于应收总额）

    private String remark;// 备注（可选）

    private List<OrderDetailDTO> details;// 商品明细列表

}
