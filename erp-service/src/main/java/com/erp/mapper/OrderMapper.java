package com.erp.mapper;

import com.erp.entity.SaleOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {

    /**
     * 删除订单
     * @param id
     */
    @Delete("delete from sale_order where id = #{id}")
    int deleteById(Integer id);

    /**
     * 插入订单表，并返回自增主键
     */
    int insert(SaleOrder order);

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    @Select("select * from sale_order where id = #{id}")
    SaleOrder getById(Integer id);
}
