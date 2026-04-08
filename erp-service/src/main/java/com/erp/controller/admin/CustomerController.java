package com.erp.controller.admin;

import com.erp.entity.Customer;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据id删除客户
     * @param id
     * @return
     */
    @DeleteMapping("/customers/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除客户:{}",id);
        customerService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改客户信息
     * @param customer
     */
    @PutMapping("/customers")
    public Result update(@RequestBody Customer customer){
        log.info("修改客户信息:{}", customer);
        customerService.update(customer);
        return Result.success();
    }

    /**
     * 根据id查询客户
     * @param
     * @return
     */
    @GetMapping("/customers/{id}")
    public Result<Customer> getById(@PathVariable Integer id){
        log.info("查询单个客户:{}", id);
        return Result.success(customerService.getById(id));
    }

    /**
     * 分页查询客户
     */
    @GetMapping("/customers/page")
    public Result page(@RequestParam(defaultValue = "1") Integer pageNum,
                      @RequestParam(defaultValue = "10") Integer pageSize,
                       @RequestParam(required = false) String name){
        PageResult pageResult = customerService.page(pageNum, pageSize, name);
        return Result.success(pageResult);
    }

}
