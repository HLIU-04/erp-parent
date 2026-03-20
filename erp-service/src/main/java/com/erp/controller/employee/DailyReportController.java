package com.erp.controller.employee;

import com.erp.dto.DailyReportDeliveryDTO;
import com.erp.result.Result;
import com.erp.service.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
