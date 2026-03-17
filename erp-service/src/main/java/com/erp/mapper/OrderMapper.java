package com.erp.mapper;

import com.erp.dto.OrderPageQueryDTO;
import com.erp.entity.SaleOrder;
import com.erp.vo.OrderPageVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 动态更新
     * @param orderUpdate
     */
    void update(SaleOrder orderUpdate);

    /**
     * 分页查询
     * @param orderPageQueryDTO
     * @return
     */
    List<OrderPageVO> pageQuery(OrderPageQueryDTO orderPageQueryDTO);
}
