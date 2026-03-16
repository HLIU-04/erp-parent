package com.erp.controller.employee;

import com.erp.dto.OrderSubmitDTO;
import com.erp.result.Result;
import com.erp.service.OrderService;
import com.erp.vo.employee.EmployeeOrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询订单信息
     * @param id
     */
    @GetMapping("/orders/{id}")
    public Result<EmployeeOrderDetailVO> getOrderDetail(@PathVariable Integer id){
        EmployeeOrderDetailVO orderDetailVO = orderService.getEmployeeOrderDetailById(id);
        return Result.success(orderDetailVO);
    }
}
