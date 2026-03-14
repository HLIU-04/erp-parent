package com.erp.service.Impl;

import com.erp.entity.Customer;
import com.erp.mapper.CustomerMapper;
import com.erp.result.PageResult;
import com.erp.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 分页查询客户
     */
    public PageResult page(Integer pageNum, Integer pageSize) {

        //设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        //执行查询
        List<Customer> customersList = customerMapper.selectAll();

        //用PageHelper提供的PageInfo封装类封装查询结果，获取总记录数等信息
        PageInfo<Customer> pageInfo = new PageInfo<>(customersList);

        //转换为自定义封装类PageResult
        PageResult pageResult = new PageResult(pageInfo.getTotal(), pageInfo.getList());

        return pageResult;
    }
}
