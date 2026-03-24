package com.erp.controller;

import com.erp.dto.LoginDTO;
import com.erp.entity.Employee;
import com.erp.result.Result;
import com.erp.service.EmployeeService;
import com.erp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO loginDTO) {
        // 验证用户
        Employee employee = employeeService.login(loginDTO.getUsername(), loginDTO.getPassword());

        // 生成 JWT
        String token = jwtUtil.generateToken(employee.getId(), employee.getUsername(), employee.getRole(), employee.getDeptId());

        // 返回 token
        return Result.success(token);
    }
}
