package com.erp.service;

import com.erp.dto.DailyReportDeliveryDTO;
import com.erp.dto.DailyReportRemainingDTO;

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
}
