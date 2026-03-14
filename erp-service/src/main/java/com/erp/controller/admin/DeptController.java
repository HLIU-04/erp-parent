package com.erp.controller.admin;

import com.erp.entity.Dept;
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
}
