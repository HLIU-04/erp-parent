package com.erp.controller.admin;

import com.erp.result.Result;
import com.erp.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("adminOrderController")
@RequestMapping("/admin")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 删除订单
     * @param id
     */
    @DeleteMapping("/orders/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除订单:{}",id);
        orderService.deleteById(id);
        return Result.success();
    }
}
