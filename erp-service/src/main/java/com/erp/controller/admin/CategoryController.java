package com.erp.controller.admin;

import com.erp.entity.Category;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param
     * @return
     */
    @PostMapping("/categories")
    public Result add(@RequestBody Category category){
        log.info("添加分类:{}", category);
        categoryService.add(category);
        return Result.success();
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/categories/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除分类:{}",id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改分类信息
     * @param category
     */
    @PutMapping("/categories")
    public Result update(@RequestBody Category category){
        log.info("修改分类信息:{}", category);
        categoryService.update(category);
        return Result.success();
    }

    /**
     * 查询单个分类
     * @param
     * @return
     */
    @GetMapping("/categories/{id}")
    public Result<Category> getById(@PathVariable Integer id){
        log.info("查询单个分类:{}", id);
        return Result.success(categoryService.getById(id));
    }

    /**
     * 分页查询分类
     */
    @GetMapping("/categories/page")
    public Result page(@RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageResult pageResult = categoryService.page(pageNum, pageSize);
        return Result.success(pageResult);
    }
}
