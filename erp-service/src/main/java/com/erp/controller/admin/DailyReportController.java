package com.erp.controller.admin;

import com.erp.result.Result;
import com.erp.service.DailyReportService;
import com.erp.vo.DailyReportDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("adminDailyReportController")
@RequestMapping("/admin")
public class DailyReportController {

    @Autowired
    private DailyReportService dailyReportService;

    /**
     * 查询日报详情
     * @param id
     * @return
     */
    @GetMapping("/daily-report/{id}")
    public Result<DailyReportDetailVO> getReportDetail(@PathVariable Integer id){
        log.info("查询日报详情:{}", id);
        return Result.success(dailyReportService.getReportDetailById(id));
    }
}
