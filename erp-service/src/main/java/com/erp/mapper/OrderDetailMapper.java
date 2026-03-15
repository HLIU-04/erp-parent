package com.erp.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper {

    /**
     * 根据订单id删除订单明细
     * @param id
     */
    @Delete("delete from sale_order_detail where order_id = #{id}")
    int deleteByOrderId(Integer id);
}
