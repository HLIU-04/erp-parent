package com.erp.mapper;

import com.erp.entity.DailyReport;
import com.erp.vo.TurnoverStatVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

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

    /**
     * 根据ID查询日报
     * @param id
     * @return
     */
    @Select("select * from daily_report where id = #{id}")
    DailyReport selectById(Integer id);

    /**
     * 分页查询日报
     * @param deptId
     * @param startDate
     * @param endDate
     * @return
     */
    List<DailyReport> selectPage(Integer deptId, LocalDate startDate, LocalDate endDate);

    /**
     * 根据日期范围查询营业额
     * @param startDate
     * @param endDate
     * @param deptId
     * @return
     */
    List<DailyReport> selectTurnoverByDateRange(LocalDate startDate, LocalDate endDate, Integer deptId);

    /**
     * 根据日期范围查询各部门营业额
     * @param startDate
     * @param endDate
     * @return
     */
    List<TurnoverStatVO> selectDeptTurnoverByDateRange(LocalDate startDate, LocalDate endDate);
}
