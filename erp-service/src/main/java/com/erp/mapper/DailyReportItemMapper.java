package com.erp.mapper;

import com.erp.entity.DailyReportItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DailyReportItemMapper {

    /**
     * 批量插入或更新
     * @param item
     */
    void insertOrUpdate(DailyReportItem item);

    /**
     * 根据日报ID查询所有明细
     * @param id
     * @return
     */
    @Select("select * from daily_report_item where report_id = #{id} order by sort_order asc")
    List<DailyReportItem> selectByReportId(Integer id);

    /**
     * 修改日报明细
     * @param item
     */
    int update(DailyReportItem item);

    /**
     * 根据日报ID删除当前明细
     * @param id
     */
    @Delete("delete from daily_report_item where report_id = #{id}")
    void deleteByReportId(Integer id);
}
