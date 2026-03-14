package com.erp.service;

import com.erp.entity.Dept;

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
}
