package com.erp.service;

import com.erp.entity.Employee;

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
}
