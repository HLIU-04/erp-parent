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
public class DailyReportItemExportVO {

    private String deptName;

    private String productName;

    private BigDecimal deliveryWeight;

    private BigDecimal remainingWeight;
}
