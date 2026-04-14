package com.erp.controller.admin;

import com.erp.dto.DailyReportUpdateDTO;
import com.erp.entity.DailyReport;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.DailyReportService;
import com.erp.vo.DailyReportDetailVO;
import com.erp.vo.DailyReportItemExportVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController("adminDailyReportController")
@RequestMapping("/admin/daily-report")
public class DailyReportController {

    @Autowired
    private DailyReportService dailyReportService;

    /**
     * 导出日报
     * @param deliveryDate
     * @param response
     * @throws IOException
     */
    @GetMapping("/exportExcel")
    public void exportDailyReport(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate deliveryDate,
                                  HttpServletResponse response) throws IOException {
        // 1. 查询数据
        List<DailyReportItemExportVO> exportData = dailyReportService.getExportData(deliveryDate);

        // 2. 加载模板文件（确保计划模板.xlsx 放在 resources/templates/ 下）
        ClassPathResource resource = new ClassPathResource("templates/计划模板.xlsx");
        Workbook workbook = WorkbookFactory.create(resource.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        // 3. 定义部门起始列（根据模板布局）
        Map<String, Integer> deptStartCol = new HashMap<>();
        deptStartCol.put("融科", 0);    // A列
        deptStartCol.put("穗丰", 5);    // F列
        deptStartCol.put("百步亭", 10); // K列
        deptStartCol.put("光华路", 15); // P列

        // 4. 建立商品名称到行号的映射（标准化：去除所有空格）
        Map<String, Integer> productRowMap = new HashMap<>();
        for (Row row : sheet) {
            int rowNum = row.getRowNum();
            if (rowNum < 2) continue; // 跳过前两行
            Cell cell = row.getCell(0);
            if (cell == null) continue;
            String rawName = cell.getStringCellValue();
            if (StringUtils.hasText(rawName)) {
                // 去除所有空白字符（包括空格、全角空格等）
                String normalizedName = rawName.replaceAll("\\s+", "");
                productRowMap.put(normalizedName, rowNum);
            }
        }

        // 5. 填充数据
        for (DailyReportItemExportVO item : exportData) {
            String deptName = item.getDeptName();
            String productName = item.getProductName();
            BigDecimal delivery = item.getDeliveryWeight();
            BigDecimal remaining = item.getRemainingWeight();

            Integer startCol = deptStartCol.get(deptName);
            if (startCol == null) continue;

            // 标准化数据库商品名（去除所有空格）
            String normalizedProduct = productName.replaceAll("\\s+", "");
            Integer rowIdx = productRowMap.get(normalizedProduct);
            if (rowIdx == null) {
                log.warn("未找到商品: {} (标准化后: {})", productName, normalizedProduct);
                continue;
            }

            Row dataRow = sheet.getRow(rowIdx);
            if (dataRow == null) continue;

            // 计划（发货）列 = startCol + 2 （因为品名列偏移0，单价列偏移1，计划列偏移2）
            Cell deliveryCell = dataRow.getCell(startCol + 2);
            if (deliveryCell == null) deliveryCell = dataRow.createCell(startCol + 2);
            deliveryCell.setCellValue(delivery != null ? delivery.doubleValue() : 0);

            // 剩货重量列 = startCol + 3
            Cell remainingCell = dataRow.getCell(startCol + 3);
            if (remainingCell == null) remainingCell = dataRow.createCell(startCol + 3);
            remainingCell.setCellValue(remaining != null ? remaining.doubleValue() : 0);
        }

        // 强制计算公式
        workbook.setForceFormulaRecalculation(true);

        // 6. 输出文件
        String fileName = "日报_" + deliveryDate + ".xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    /**
     * 查询日报详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
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
    @GetMapping("/page")
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
    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody DailyReportUpdateDTO dailyReportUpdateDTO){
        dailyReportService.update(id, dailyReportUpdateDTO);
        return Result.success();
    }
}
