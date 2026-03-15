package com.erp.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    /**
     * 删除订单
     * @param id
     */
    @Delete("delete from sale_order where id = #{id}")
    int deleteById(Integer id);
}
