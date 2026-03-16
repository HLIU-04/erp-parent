package com.erp.mapper;

import com.erp.entity.SaleOrderDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    /**
     * 根据订单id删除订单明细
     * @param id
     */
    @Delete("delete from sale_order_detail where order_id = #{id}")
    int deleteByOrderId(Integer id);

    /**
     * 插入订单明细
     */
    int insertBatch(List<SaleOrderDetail> details);
}
