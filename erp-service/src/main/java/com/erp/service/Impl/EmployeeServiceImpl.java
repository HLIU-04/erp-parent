package com.erp.service.Impl;

import com.erp.entity.Employee;
import com.erp.mapper.EmployeeMapper;
import com.erp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 添加员工
     * @param employee
     */
    public void add(Employee employee) {
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employeeMapper.add(employee);
    }

    /**
     * 根据id删除员工
     * @param id
     */
    public void deleteById(Integer id) {
        employeeMapper.deleteById(id);
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    public Employee getById(Integer id) {
        return employeeMapper.getById(id);
    }

    /**
     * 修改员工信息
     * @param employee
     */
    public void update(Employee employee) {
        employee.setUpdateTime(LocalDateTime.now());
        employeeMapper.update(employee);
    }
}
