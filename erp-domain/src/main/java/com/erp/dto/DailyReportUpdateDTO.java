package com.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyReportUpdateDTO {

    private List<DailyReportItemUpdateDTO> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DailyReportItemUpdateDTO {

        private Integer productId;

        private BigDecimal unitPrice;

        private BigDecimal deliveryWeight;

        private BigDecimal remainingWeight;
    }

}

