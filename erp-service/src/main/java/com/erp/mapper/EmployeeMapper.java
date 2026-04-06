package com.erp.mapper;

import com.erp.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    /**
     * 添加员工
     * @param employee
     */
    @Insert("insert into employee (username, name, password, sex, phone, dept_id, status, create_time, update_time) " +
            "values (#{username}, #{name}, #{password}, #{sex}, #{phone}, #{deptId}, #{status}, #{createTime}, #{updateTime})")
    void add(Employee employee);

    /**
     * 根据id删除员工
     * @param id
     */
    @Delete("delete from employee where id = #{id}")
    void deleteById(Integer id);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @Select("select * from employee where id = #{id}")
    Employee getById(Integer id);

    /**
     * 修改员工信息
     * @param employee
     */
    void update(Employee employee);

    /**
     * 分页查询所有员工
     * @return
     */
    List<Employee> pageQuery(String  name);

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee selectByUsername(String username);
}
