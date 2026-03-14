package com.erp.controller.admin;

import com.erp.entity.Product;
import com.erp.result.Result;
import com.erp.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 删除商品
     * @param id
     * @return
     */
    @DeleteMapping("/products/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除商品:{}",id);
        productService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改商品信息
     * @param product
     */
    @PutMapping("/products")
    public Result update(@RequestBody Product product){
        log.info("修改商品信息:{}", product);
        productService.update(product);
        return Result.success();
    }

    /**
     * 查询商品信息
     * @param id
     */
    @GetMapping("/products/{id}")
    public Result get(@PathVariable Integer id){
        log.info("查询商品信息:{}", id);
        return Result.success(productService.getById(id));
    }
}
