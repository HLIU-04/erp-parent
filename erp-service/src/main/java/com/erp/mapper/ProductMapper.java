package com.erp.mapper;

import com.erp.entity.Product;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * 根据id删除商品
     * @param id
     */
    @Delete("delete from product where id = #{id}")
    void deleteById(Integer id);
}
