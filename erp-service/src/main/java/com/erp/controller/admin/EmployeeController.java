package com.erp.controller.admin;

import com.erp.entity.Employee;
import com.erp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.erp.result.Result;

@Slf4j
@RestController
@RequestMapping("/admin")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 添加员工
     * @param employee
     * @return
     */
    @PostMapping("employees")
    public Result add(@RequestBody Employee employee){
        log.info("添加员工");
        employeeService.add(employee);
        return Result.success();
    }

    /**
     * 根据id删除员工
     * @param id
     * @return
     */
    @DeleteMapping("/employees/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除员工");
        employeeService.deleteById(id);
        return Result.success();
    }
}
