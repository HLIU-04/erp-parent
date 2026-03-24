package com.erp.service;

import com.erp.entity.Employee;
import com.erp.result.PageResult;

public interface EmployeeService {

    /**
     * 添加员工
     * @param employee
     */
    void add(Employee employee);

    /**
     * 根据id删除员工
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 修改员工信息
     * @param employee
     */
    void update(Employee employee);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Employee getById(Integer id);

    /**
     * 分页查询员工
     */
    PageResult page(Integer pageNum, Integer pageSize);

    /**
     * 员工登录
     * @param username
     * @param password
     * @return
     */
    Employee login(String username, String password);
}
