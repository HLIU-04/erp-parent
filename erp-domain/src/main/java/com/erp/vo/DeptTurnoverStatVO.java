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
public class DeptTurnoverStatVO {

    private Integer deptId;

    private String deptName;

    private BigDecimal totalAmount;
}
