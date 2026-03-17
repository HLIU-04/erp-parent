package com.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderUpdateDTO {

    private Integer id;

    private Integer customerId;

    private BigDecimal actualAmount;

    private String payMethod;

    private String remark;

    private List<OrderDetailDTO> details;//订单明细(如果客户端上传则覆盖更新)
}
