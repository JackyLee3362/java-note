package edu.note;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * @author lijie206
 * @date 2026-06-12
 */
public class POIExcel {

    static class User {

        Long userId;
        String username;
        String email;
        Integer status;

        User(Long userId, String username, String email, Integer status) {
            this.userId = userId;
            this.username = username;
            this.email = email;
            this.status = status;
        }
    }

    // 模拟数据
    private static List<User> mockData(int count) {
        List<User> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            list.add(new User(
                    (long) i,
                    "用户_" + i,
                    "user" + i + "@example.com",
                    i % 3));
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        String outputPath = "demo_output.xlsx";
        String[] headers = { "用户ID", "用户名", "邮箱", "状态" };
        List<User> dataList = mockData(1000);

        // SXSSFWorkbook: 流式写入，内存中只保留 100 行，适合大数据量
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100);
                FileOutputStream fos = new FileOutputStream(outputPath)) {

            Sheet sheet = workbook.createSheet("用户数据");

            // 写表头
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // 写数据行
            for (int i = 0; i < dataList.size(); i++) {
                User user = dataList.get(i);
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(user.userId);
                row.createCell(1).setCellValue(user.username);
                row.createCell(2).setCellValue(user.email);
                row.createCell(3).setCellValue(user.status);
            }

            workbook.write(fos);
        }

        System.out.println("Excel 生成完成: " + outputPath);
        System.out.println("共写入 " + dataList.size() + " 条数据");
    }
}
