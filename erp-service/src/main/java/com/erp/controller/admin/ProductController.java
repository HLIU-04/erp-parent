package com.erp.controller.admin;

import com.erp.entity.Product;
import com.erp.result.Result;
import com.erp.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 添加商品
     * @param product
     * @return
     */
    @PostMapping("/products")
    public Result add(@RequestBody Product product){
        log.info("添加商品:{}", product);
        productService.add(product);
        return Result.success();
    }
}
