package com.erp.mapper;

import com.erp.entity.Category;
import com.erp.entity.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 添加分类
     * @param category
     */
    @Insert("insert into category(name, parent_id, create_time, update_time)" +
    "values (#{name}, #{parentId}, #{createTime}, #{updateTime})")
    void add(Category category);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Integer id);

    /**
     * 修改分类信息
     * @param category
     */
    void update(Category category);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @Select("select * from category where id = #{id}")
    Category getById(Integer id);

    /**
     * 查询所有分类
     * @return
     */
    List<Category> pageQuery(String  name);
}
