package com.erp.controller.employee;

import com.erp.dto.DailyReportDeliveryDTO;
import com.erp.dto.DailyReportRemainingDTO;
import com.erp.result.Result;
import com.erp.service.DailyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
