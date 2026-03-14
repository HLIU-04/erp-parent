package com.erp.mapper;

import com.erp.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 修改商品信息
     * @param product
     */
    void update(Product product);

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    Product getById(Integer id);

    /**
     * 查询所有商品
     * @return
     */
    @Select("select * from product")
    List<Product> selectAll();
}
