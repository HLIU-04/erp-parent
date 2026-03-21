package com.erp.controller.admin;

import com.erp.dto.DailyReportUpdateDTO;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.DailyReportService;
import com.erp.vo.DailyReportDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    /**
     * 分页查询
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
                                   @RequestParam(required = false) Integer deptId,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                   @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        PageResult pageResult = dailyReportService.pageQuery(pageNum, pageSize, deptId, startDate, endDate);
        return Result.success(pageResult);
    }

    /**
     * 修改日报明细
     * @param dailyReportUpdateDTO
     * @return
     */
    @PutMapping("/daily-report/{id}")
    public Result update(@PathVariable Integer id, @RequestBody DailyReportUpdateDTO dailyReportUpdateDTO){
        dailyReportService.update(id, dailyReportUpdateDTO);
        return Result.success();
    }
}
