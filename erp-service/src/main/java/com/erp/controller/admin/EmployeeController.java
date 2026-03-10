package com.erp.controller.admin;

import com.erp.entity.Employee;
import com.erp.result.PageResult;
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
    @PostMapping("/employees")
    public Result add(@RequestBody Employee employee){
        log.info("添加员工:{}", employee);
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
        log.info("删除员工:{}",id);
        employeeService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改员工信息
     * @param employee
     */
    @PutMapping("/employees")
    public Result update(@RequestBody Employee employee){
        log.info("修改员工信息:{}", employee);
        employeeService.update(employee);
        return Result.success();
    }

    /**
     * 查询单个员工
     * @param
     * @return
     */
    @GetMapping("/employees/{id}")
    public Result<Employee> getById(@PathVariable Integer id){
        log.info("查询单个员工:{}", id);
        return Result.success(employeeService.getById(id));
    }

    /**
     * 分页查询员工
     */
    @GetMapping("/employees/page")
    public Result page(@RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageResult pageResult = employeeService.page(pageNum, pageSize);
        return Result.success(pageResult);
    }
}
