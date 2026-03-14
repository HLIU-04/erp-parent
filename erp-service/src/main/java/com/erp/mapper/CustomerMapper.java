package com.erp.mapper;

import com.erp.entity.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {

    /**
     * 添加客户
     * @param customer
     */
    @Insert("insert into customer(name, phone, address, wechat, create_time, update_time)" +
            "values (#{name}, #{phone}, #{address}, #{wechat}, #{createTime}, #{updateTime})")
    void add(Customer customer);
}
