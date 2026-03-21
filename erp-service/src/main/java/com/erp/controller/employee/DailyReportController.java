package com.erp.controller.employee;

import com.erp.dto.DailyReportDeliveryDTO;
import com.erp.dto.DailyReportRemainingDTO;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.DailyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    @PostMapping("/daily-report")
    public Result delivery(@RequestBody DailyReportDeliveryDTO dailyReportDeliveryDTO){
        dailyReportService.delivery(dailyReportDeliveryDTO);
        return Result.success();
    }

    /**
     * 添加日报中剩余商品
     * @param dailyReportRemainingDTO
     * @return
     */
    @PutMapping("/remaining")
    public Result remaining(@RequestBody DailyReportRemainingDTO dailyReportRemainingDTO){
        dailyReportService.remaining(dailyReportRemainingDTO);
        return Result.success();
    }

    /**
     * 分页查询日报
     * @param pageNum
     * @param pageSize
     * @param deptId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/daily-report/page")
    public Result<PageResult> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam Integer deptId,  // TODO: 登录后改为从token获取当前员工部门ID
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        PageResult pageResult = dailyReportService.pageQuery(pageNum, pageSize, deptId, startDate, endDate);
        return Result.success(pageResult);
    }
}
