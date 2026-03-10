package com.erp.mapper;

import com.erp.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    /**
     * 添加员工
     * @param employee
     */
    @Insert("insert into employee(username, name, sex, phone, dept_id, create_time, update_time)" +
            "values (#{username}, #{name}, #{sex}, #{phone}, #{deptId}, #{createTime}, #{updateTime})")
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
}
