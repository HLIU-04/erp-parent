package com.erp.service;

import com.erp.entity.Customer;

public interface CustomerService {

    /**
     * 添加客户
     * @param customer
     */
    void add(Customer customer);

    /**
     * 根据id删除客户
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 修改客户信息
     * @param customer
     */
    void update(Customer customer);
}
