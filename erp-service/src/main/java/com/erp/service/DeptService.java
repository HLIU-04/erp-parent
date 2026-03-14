package com.erp.service;

import com.erp.entity.Dept;
import com.erp.result.PageResult;

public interface DeptService {

    /**
     * 添加部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 根据id删除部门
     * @param id
     */
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
    Dept getById(Integer id);

    /**
     * 分页查询部门
     */
    PageResult page(Integer pageNum, Integer pageSize);
}
