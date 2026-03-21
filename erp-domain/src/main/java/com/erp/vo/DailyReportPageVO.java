package com.erp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyReportPageVO {

    private Integer id;

    private LocalDate deliveryDate;

    private Integer deptId;

    private String deptName;

    private BigDecimal totalAmount;
}
