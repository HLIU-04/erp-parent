package com.erp.mapper;

import com.erp.entity.Customer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 修改客户信息
     * @param customer
     */
    void update(Customer customer);

    /**
     * 根据id查询客户
     * @param id
     * @return
     */
    @Select("select * from customer where id = #{id}")
    Customer getById(Integer id);

    /**
     * 分页查询所有客户
     * @return
     */
    @Select("select * from customer")
    List<Customer> selectAll();
}
