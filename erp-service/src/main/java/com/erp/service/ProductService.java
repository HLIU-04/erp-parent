package com.erp.service;

import com.erp.entity.Product;

public interface ProductService {

    /**
     * 添加商品
     * @param product
     */
    void add(Product product);

    /**
     * 根据id删除商品
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 修改商品信息
     * @param product
     */
    void update(Product product);

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    Product getById(Integer id);
}
