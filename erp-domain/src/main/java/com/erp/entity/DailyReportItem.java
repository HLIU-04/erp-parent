package com.erp.entity;

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
public class DailyReportItem {

    private Integer id;

    private Integer reportId;//关联日报ID

    private Integer productId;

    private BigDecimal unitPrice;//当日单价（快照记录）

    private BigDecimal deliveryWeight;//发货重量

    private BigDecimal remainingWeight;//剩余重量

    private Integer sortOrder;//打印顺序

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
