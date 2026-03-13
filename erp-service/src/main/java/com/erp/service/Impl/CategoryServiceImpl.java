package com.erp.service.Impl;

import com.erp.entity.Category;
import com.erp.entity.Employee;
import com.erp.mapper.CategoryMapper;
import com.erp.result.PageResult;
import com.erp.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     * @param category
     */
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.add(category);
    }

    /**
     * 根据id删除分类
     * @param id
     */
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }

    /**
     * 修改分类信息
     * @param category
     */
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    /**
     * 查询单个分类
     * @param id
     * @return
     */
    @Override
    public Category getById(Integer id) {
        return categoryMapper.getById(id);
    }

    /**
     * 查询所有分类
     * @return
     */
    public PageResult page(Integer pageNum, Integer pageSize) {

        //设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        //执行查询
        List<Category> categoryList = categoryMapper.selectAll();

        //用 PageHelper 提供的 PageInfo 封装类封装查询结果，获取总记录数等信息
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);

        //转换为自定义封装类 PageResult
        PageResult pageResult = new PageResult(pageInfo.getTotal(), pageInfo.getList());

        return pageResult;
    }
}
