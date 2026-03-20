package com.erp.service;

import com.erp.dto.DailyReportDeliveryDTO;
import com.erp.dto.DailyReportRemainingDTO;
import com.erp.vo.DailyReportDetailVO;

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
}
