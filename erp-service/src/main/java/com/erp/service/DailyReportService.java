package com.erp.service;

import com.erp.dto.DailyReportDeliveryDTO;

public interface DailyReportService {

    /**
     * 添加日报
     * @param dailyReportDeliveryDTO
     */
    void delivery(DailyReportDeliveryDTO dailyReportDeliveryDTO);
}
