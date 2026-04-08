package com.erp.controller.employee;

import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("employeeProductController")
@RequestMapping("/employee")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 分页查询商品信息
     */
    @GetMapping("/products/page")
    public Result<PageResult> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam(required = false) String name){
        PageResult pageResult = productService.page(pageNum, pageSize, name);
        return Result.success(pageResult);
    }
}
