package com.erp.service;

import com.erp.entity.Category;
import com.erp.result.PageResult;

public interface CategoryService {

    /**
     * 添加分类
     * @param category
     */
    void add(Category category);

    /**
     * 根据id删除分类
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 修改分类信息
     * @param category
     */
    void update(Category category);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    Category getById(Integer id);

    /**
     * 分页查询分类
     */
    PageResult page(Integer pageNum, Integer pageSize);
}
