package com.erp.mapper;

import com.erp.entity.DailyReportItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DailyReportItemMapper {

    /**
     * 批量插入或更新
     * @param item
     */
    void insertOrUpdate(DailyReportItem item);
}
