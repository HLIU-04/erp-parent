package com.erp.dto;

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
public class DailyReportRemainingDTO {

    private Integer deptId;

    private LocalDate deliveryDate;   // 对应发货日期（实际销售日）

    private List<RemainingItemDTO> items;

    @Data
    public static class RemainingItemDTO {

        private Integer productId;

        private BigDecimal remainingWeight;
    }
}
