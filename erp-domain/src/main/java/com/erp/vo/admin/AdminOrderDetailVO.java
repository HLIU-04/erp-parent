package com.erp.vo.admin;

import com.erp.vo.OrderItemVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminOrderDetailVO {

    private Integer id;

    private String orderNo;

    private Integer customerId;

    private String customerName;

    private Integer operatorId;

    private String operatorName;

    private Integer deptId;

    private String deptName;

    private BigDecimal totalAmount;

    private BigDecimal actualAmount;

    private String payMethod;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleTime;

    private String remark;

    private List<OrderItemVO> items;
}
