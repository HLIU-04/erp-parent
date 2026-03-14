package com.erp.controller.admin;

import com.erp.entity.Customer;
import com.erp.result.Result;
import com.erp.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 添加客户
     * @param customer
     * @return
     */
    @PostMapping("/customers")
    public Result add(@RequestBody Customer customer){
        log.info("添加客户:{}", customer);
        customerService.add(customer);
        return Result.success();
    }
}
