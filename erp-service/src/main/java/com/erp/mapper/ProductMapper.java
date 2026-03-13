package com.erp.mapper;

import com.erp.entity.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    /**
     * 添加商品
     * @param product
     */
    @Insert("insert into product(name, category_id, price, unit)" +
    "values (#{name}, #{categoryId}, #{price}, #{unit})")
    void add(Product product);
}
