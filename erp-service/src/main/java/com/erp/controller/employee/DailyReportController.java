package com.erp.controller.employee;

import com.erp.dto.DailyReportDeliveryDTO;
import com.erp.dto.DailyReportRemainingDTO;
import com.erp.exception.BusinessException;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.DailyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController("employeeDailyReportController")
@RequestMapping("/employee")
public class DailyReportController {

    @Autowired
    private DailyReportService dailyReportService;

    /**
     * 添加日报
     * @param dailyReportDeliveryDTO
     * @return
     */
    @PostMapping("/daily-report/delivery")
    public Result delivery(@RequestBody DailyReportDeliveryDTO dailyReportDeliveryDTO){
        dailyReportService.delivery(dailyReportDeliveryDTO);
        return Result.success();
    }

    /**
     * 添加日报中剩余商品
     * @param dailyReportRemainingDTO
     * @return
     */
    @PostMapping("/daily-report/remaining")
    public Result remaining(@RequestBody DailyReportRemainingDTO dailyReportRemainingDTO){
        dailyReportService.remaining(dailyReportRemainingDTO);
        return Result.success();
    }

    /**
     * 分页查询日报
     * @param pageNum
     * @param pageSize
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/daily-report/page")
    public Result<PageResult> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        // 从 SecurityContext 获取当前用户的部门 ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer deptId = null;
        if (authentication != null && authentication.getDetails() instanceof Integer) {
            deptId = (Integer) authentication.getDetails();
        }
        if (deptId == null) {
            throw new BusinessException("无法获取当前用户的部门信息");
        }
        PageResult pageResult = dailyReportService.pageQuery(pageNum, pageSize, deptId, startDate, endDate);
        return Result.success(pageResult);
    }
}
