package com.erp.mapper;

import com.erp.entity.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DeptMapper {

    /**
     * 添加部门
     * @param dept
     */
    @Insert("insert into dept(name, status, create_time, update_time)" +
            "values (#{name}, #{status}, #{createTime}, #{updateTime})")
    void add(Dept dept);

    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 修改部门信息
     * @param dept
     */
    void update(Dept dept);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);
}
