package com.erp.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPageVO {

    private Integer id;

    private String orderNo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleTime;

    private BigDecimal totalAmount;

    private BigDecimal actualAmount;

    private String customerName;

    private String customerPhone;

    private String deptName;

    private String operatorName;

    private String payMethod;
}
