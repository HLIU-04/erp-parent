package com.erp.service.Impl;

import com.erp.entity.Employee;
import com.erp.mapper.EmployeeMapper;
import com.erp.result.PageResult;
import com.erp.service.EmployeeService;
import com.erp.exception.LoginException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
     * 修改员工信息
     * @param employee
     */
    public void update(Employee employee) {
        employee.setUpdateTime(LocalDateTime.now());
        employeeMapper.update(employee);
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
     * 分页查询员工
     */
    public PageResult page(Integer pageNum, Integer pageSize) {

        //设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        //执行查询
        List<Employee> employeeList = employeeMapper.selectAll();

        //用PageHelper提供的PageInfo封装类封装查询结果，获取总记录数等信息
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);

        //转换为自定义封装类PageResult
        PageResult pageResult = new PageResult(pageInfo.getTotal(), pageInfo.getList());

        return pageResult;
    }

    /**
     * 员工登录
     * @param username
     * @param password
     * @return
     */
    public Employee login(String username, String password) {

        // 根据用户名查询员工
        Employee employee = employeeMapper.selectByUsername(username);
        if (employee == null) {
            throw new LoginException("用户名不存在");
        }

        //验证密码
        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new LoginException("密码错误");
        }
        return employee;
    }
}
