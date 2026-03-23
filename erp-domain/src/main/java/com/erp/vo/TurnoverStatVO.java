package com.erp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

//日期营业额统计
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TurnoverStatVO {

    private LocalDate date;

    private BigDecimal totalAmount;
}
