package com.erp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemVO {

    private String productName;

    private BigDecimal quantity;

    private BigDecimal unitPrice;//单位价格

    private BigDecimal totalPrice;//总价
}
