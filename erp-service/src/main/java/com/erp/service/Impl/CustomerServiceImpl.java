package com.erp.service.Impl;

import com.erp.entity.Customer;
import com.erp.mapper.CustomerMapper;
import com.erp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void add(Customer customer) {
        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());
        customerMapper.add(customer);
    }

    /**
     * 根据id删除客户
     * @param id
     */
    public void deleteById(Integer id) {
        customerMapper.deleteById(id);
    }

    /**
     * 修改客户信息
     * @param customer
     */
    public void update(Customer customer) {
        customer.setUpdateTime(LocalDateTime.now());
        customerMapper.update(customer);
    }

    /**
     * 根据id查询客户
     * @param id
     * @return
     */
    public Customer getById(Integer id) {
        return customerMapper.getById(id);
    }
}
