package com.erp.controller.admin;

import com.erp.entity.Category;
import com.erp.result.Result;
import com.erp.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 添加分类
     * @param
     * @return
     */
    @PostMapping("/categories")
    public Result add(@RequestBody Category category){
        log.info("添加分类:{}", category);
        categoryService.add(category);
        return Result.success();
    }
}
