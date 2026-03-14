package com.erp.controller.admin;

import com.erp.entity.Dept;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info("添加部门:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除部门:{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改部门信息
     * @param dept
     */
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("修改部门信息:{}", dept);
        deptService.update(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @GetMapping("/depts/{id}")
    public Result<Dept> getById(@PathVariable Integer id){
        log.info("查询单个部门:{}", id);
        return Result.success(deptService.getById(id));
    }

    /**
     * 分页查询部门
     */
    @GetMapping("/depts/page")
    public Result page(@RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageResult pageResult = deptService.page(pageNum, pageSize);
        return Result.success(pageResult);
    }
}
