package com.erp.controller.admin;

import com.erp.result.Result;
import com.erp.service.OrderService;
import com.erp.vo.admin.AdminOrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询订单信息
     * @param id
     */
    @GetMapping("/orders/{id}")
    public Result<AdminOrderDetailVO> getOrderDetail(@PathVariable Integer id){
        log.info("查询订单信息:{}", id);
        AdminOrderDetailVO orderDetailVO = orderService.getAdminOrderDetailById(id);
        return Result.success(orderDetailVO);
    }
}
