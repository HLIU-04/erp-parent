package com.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 订单提交时的商品明细项
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDTO {

    private Integer productId;

    private BigDecimal quantity;// 数量

    private BigDecimal unitPrice;//单价
}
