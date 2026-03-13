package com.erp.service;

import com.erp.entity.Category;

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
}
