package com.erp.controller.employee;

import com.erp.dto.OrderSubmitDTO;
import com.erp.result.Result;
import com.erp.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("employeeOrderController")
@RequestMapping("/employee")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 添加订单
     * @param orderSubmitDTO
     */
    @PostMapping("/orders")
    public Result add(@RequestBody OrderSubmitDTO orderSubmitDTO){
        log.info("添加订单:{}", orderSubmitDTO);
        orderService.submit(orderSubmitDTO);
        return Result.success();
    }
}
