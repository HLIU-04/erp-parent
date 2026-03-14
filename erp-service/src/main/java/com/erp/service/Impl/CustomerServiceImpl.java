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
}
