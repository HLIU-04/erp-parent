package com.erp.service;

import com.erp.dto.DailyReportDeliveryDTO;
import com.erp.dto.DailyReportRemainingDTO;
import com.erp.dto.DailyReportUpdateDTO;
import com.erp.result.PageResult;
import com.erp.vo.DailyReportDetailVO;
import com.erp.vo.DailyReportItemExportVO;
import com.erp.vo.DeptTurnoverStatVO;
import com.erp.vo.TurnoverStatVO;

import java.time.LocalDate;
import java.util.List;

public interface DailyReportService {

    /**
     * 添加日报
     * @param dailyReportDeliveryDTO
     */
    void delivery(DailyReportDeliveryDTO dailyReportDeliveryDTO);

    /**
     * 添加日报中剩余重量
     * @param dailyReportRemainingDTO
     */
    void remaining(DailyReportRemainingDTO dailyReportRemainingDTO);

    /**
     * 根据ID查询日报信息
     * @param id
     * @return
     */
    DailyReportDetailVO getReportDetailById(Integer id);

    /**
     * 分页查询日报
     * @param pageNum
     * @param pageSize
     * @param deptId
     * @param startDate
     * @param endDate
     * @return
     */
    PageResult pageQuery(Integer pageNum, Integer pageSize, Integer deptId, LocalDate startDate, LocalDate endDate);

    /**
     * 修改日报信息
     * @param id
     * @param dailyReportUpdateDTO
     */
    void update(Integer id, DailyReportUpdateDTO dailyReportUpdateDTO);

    /**
     * 根据日期范围查询营业额
     * @param startDate
     * @param endDate
     * @param deptId
     * @return
     */
    List<TurnoverStatVO> getTurnoverByDateRange(LocalDate startDate, LocalDate endDate, Integer deptId);

    /**
     * 根据日期范围查询各部门营业额
     * @param startDate
     * @param endDate
     * @return
     */
    List<DeptTurnoverStatVO> getDeptTurnoverByDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * 根据发货日期查询日报数据
     * @param deliveryDate
     * @return
     */
    List<DailyReportItemExportVO> getExportData(LocalDate deliveryDate);
}
