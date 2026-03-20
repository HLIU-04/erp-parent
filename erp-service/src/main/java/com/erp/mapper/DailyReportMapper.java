package com.erp.mapper;

import com.erp.entity.DailyReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

@Mapper
public interface DailyReportMapper {
    /**
     * 根据部门ID和发货日期查询日报
     * @param deptId
     * @param deliveryDate
     * @return
     */
    @Select("select * from daily_report where dept_id = #{deptId} and delivery_date = #{deliveryDate}")
    DailyReport selectByDeptAndDate(Integer deptId, LocalDate deliveryDate);

    /**
     * 添加日报
     * @param report
     */
    void insert(DailyReport report);

    /**
     * 修改日报
     * @param report
     * @return
     */
    int update(DailyReport report);
}
