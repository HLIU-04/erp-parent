package com.erp.service.Impl;


import com.erp.entity.Product;
import com.erp.mapper.ProductMapper;
import com.erp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 添加商品
     * @param product
     */
    public void add(Product product) {
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        productMapper.add(product);
    }

    /**
     * 根据id删除商品
     * @param id
     */
    public void deleteById(Integer id) {
        productMapper.deleteById(id);
    }

    /**
     * 修改商品信息
     * @param product
     */
    public void update(Product product) {
        product.setUpdateTime(LocalDateTime.now());
        productMapper.update(product);
    }
}
