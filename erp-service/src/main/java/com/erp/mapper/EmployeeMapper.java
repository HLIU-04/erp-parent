package com.erp.mapper;

import com.erp.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {
    /**
     * 添加员工
     * @param employee
     */
    @Insert("insert into employee(username, name, sex, phone, dept_id, create_time, update_time)" +
            "values (#{username}, #{name}, #{sex}, #{phone}, #{deptId}, #{createTime}, #{updateTime})")
    void add(Employee employee);
}
