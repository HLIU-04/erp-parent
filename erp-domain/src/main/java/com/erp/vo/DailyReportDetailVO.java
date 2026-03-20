package com.erp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyReportDetailVO {

    private Integer id;

    private LocalDate deliveryDate;// 发货日期

    private Integer deptId;

    private String deptName;// 部门名称（关联部门表）

    private BigDecimal totalAmount;// 营业额（直接取主表）

    private List<DailyReportItemVO> items;

    @Data
    @Builder
    public static class DailyReportItemVO {

        private Integer productId;

        private String productName;// 商品名称

        private BigDecimal unitPrice;// 单价

        private BigDecimal deliveryWeight;// 发货重量

        private BigDecimal remainingWeight;// 剩货重量

        private BigDecimal deliveryAmount;// 发货金额（计算得出）

        private BigDecimal remainingAmount;// 剩货金额（计算得出）

        private Integer sortOrder;
    }
}
