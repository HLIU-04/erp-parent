package com.erp.service.Impl;

import com.erp.entity.Dept;
import com.erp.mapper.DeptMapper;
import com.erp.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 添加部门
     * @param dept
     */
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    /**
     * 根据id删除部门
     * @param id
     */
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    /**
     * 修改部门信息
     * @param dept
     */
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
