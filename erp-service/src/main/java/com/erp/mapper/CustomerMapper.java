package com.erp.mapper;

import com.erp.entity.Customer;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * 根据id删除客户
     * @param id
     */
    @Delete("delete from customer where id = #{id}")
    void deleteById(Integer id);
}
