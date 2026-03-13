package com.erp.mapper;

import com.erp.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    /**
     * 添加分类
     * @param category
     */
    @Insert("insert into category(name, parent_id, status, create_time, update_time)" +
    "values (#{name}, #{parentId}, #{status}, #{createTime}, #{updateTime})")
    void add(Category category);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Integer id);
}
