package com.erp.controller.admin;

import com.erp.result.Result;
import com.erp.service.DailyReportService;
import com.erp.vo.TurnoverStatVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/statistics")
public class StatisticsController {

    @Autowired
    private DailyReportService dailyReportService;

    /**
     * 根据日期范围查询营业额
     * @param startDate
     * @param endDate
     * @param deptId
     * @return
     */
    @GetMapping("/turnover")
    public Result<List<TurnoverStatVO>> getTurnover(@RequestParam@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                    @RequestParam@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                    @RequestParam(required = false) Integer deptId) {
        List<TurnoverStatVO> list = dailyReportService.getTurnoverByDateRange(startDate, endDate, deptId);
        return Result.success(list);
    }

    /**
     * 根据日期范围查询各部门营业额
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/deptTurnover")
    public Result<List<TurnoverStatVO>> getDeptTurnover(@RequestParam@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                        @RequestParam@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<TurnoverStatVO> list = dailyReportService.getDeptTurnoverByDateRange(startDate, endDate);

        return Result.success(list);
    }
}
