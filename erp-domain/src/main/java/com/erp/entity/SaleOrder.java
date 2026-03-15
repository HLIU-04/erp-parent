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
public class SaleOrder {

    private Integer id;

    private String orderNo;//订单编号

    private Integer customerId;//客户ID(可为空，即线下销售散客)

    private Integer operatorId;//操作员ID

    private Integer deptId;//部门(摊位)ID

    private BigDecimal totalAmount;//应收金额

    private BigDecimal actualAmount;//实收金额

    private String payMethod;//支付方式

    private Integer status;//订单状态:1-正常 0-取消

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleTime;//销售时间

    private String remark;//订单备注

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
