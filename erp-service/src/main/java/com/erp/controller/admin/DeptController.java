package com.erp.controller.admin;

import com.erp.entity.Dept;
import com.erp.result.Result;
import com.erp.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
