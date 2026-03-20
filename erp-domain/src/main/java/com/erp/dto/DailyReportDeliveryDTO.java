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
public class DailyReportDeliveryDTO {

    private Integer deptId;

    private LocalDate deliveryDate;

    private List<DeliveryItemDTO> items;

    @Data
    public static class DeliveryItemDTO {

        private Integer productId;

        private BigDecimal deliveryWeight;//发货重量
    }
}
