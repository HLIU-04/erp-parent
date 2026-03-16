package com.erp.vo.employee;

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
public class EmployeeOrderDetailVO {

    private String orderNo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleTime;

    private BigDecimal totalAmount;

    private BigDecimal actualAmount;

    private String payMethod;

    private List<OrderItemVO> items;
}
