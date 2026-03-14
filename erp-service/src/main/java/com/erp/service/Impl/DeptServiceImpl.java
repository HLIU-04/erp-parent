package com.erp.service.Impl;

import com.erp.entity.Dept;
import com.erp.mapper.DeptMapper;
import com.erp.result.PageResult;
import com.erp.service.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    /**
     * 查询所有部门
     * @return
     */
    public PageResult page(Integer pageNum, Integer pageSize) {

        //设置分页参数
        PageHelper.startPage(pageNum, pageSize);

        //执行查询
        List<Dept> deptList= deptMapper.selectAll();

        //用PageHelper提供的PageInfo封装类封装查询结果，获取总记录数等信息
        PageInfo<Dept> pageInfo = new PageInfo<>(deptList);

        //转换为自定义封装类PageResult
        PageResult pageResult = new PageResult(pageInfo.getTotal(), pageInfo.getList());

        return pageResult;
    }
}
