package com.erp.service.Impl;


import com.erp.entity.Product;
import com.erp.mapper.ProductMapper;
import com.erp.result.PageResult;
import com.erp.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    public Product getById(Integer id) {
        return productMapper.getById(id);
    }

    /**
     * 分页查询所有商品
     */
    public PageResult page(Integer pageNum, Integer pageSize) {

        //设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        //执行查询
        List<Product> productList = productMapper.selectAll();

        //用PageHelper提供的PageInfo封装类封装查询结果，获取总记录数等信息
        PageInfo<Product> pageInfo = new PageInfo<>(productList);

        //转换为自定义封装类PageResult
        PageResult pageResult = new PageResult(pageInfo.getTotal(), pageInfo.getList());

        return pageResult;
    }
}
