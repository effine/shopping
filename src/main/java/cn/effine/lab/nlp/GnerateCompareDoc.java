package cn.effine.lab.nlp;


import cn.effine.lab.nlp.baidu.NplTest;
import cn.effine.lab.nlp.tencent.Demo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * 生产百度云 vs 腾讯云 对比文档
 */
public class GnerateCompareDoc {

    // 文件路径
    private static final String filepath = "/share/tencen_vs_baidu.xls";

    // 待分词原词
    private static final String[] sourceWords = {
            "LINDT瑞士莲软心 - 牛奶巧克力礼盒168g（瑞士进口 盒）",
            "MARTELL马爹利XO干邑（进口食品 瓶装 1.5L)",
            "倍乐果锥形棒棒糖157g（法国进口 盒）",
            "【买一赠一】奇奥辣味玉米片125g(德国进口 袋)",
            "【自营】JASON捷森五种水果麦片1000g（德国进口 袋）"
    };


    public static void main(String[] args) throws IOException {

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("腾讯云 vs 百度云");

        // 标题
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("功能名称");
        row.createCell(1).setCellValue("原词");
        row.createCell(2).setCellValue("百度云 一级分词");
        row.createCell(3).setCellValue("百度云 二级分词");
        row.createCell(4).setCellValue("腾讯云分词");
        row.createCell(6).setCellValue("百度云原始返回值");
        row.createCell(7).setCellValue("腾讯云原始返回值");

        int ros = sourceWords.length;
        for (int i = 0; i < ros; i++) {

            String word = sourceWords[i];
            Map<String, String> baiduMaps = NplTest.NLPLexer(word);
            Map<String, String> tencentMaps = Demo.getTextDependency(word);

            Row tempRow = sheet.createRow(i + 1);
            tempRow.createCell(1).setCellValue(word);
            tempRow.createCell(2).setCellValue(baiduMaps.get("one"));
            tempRow.createCell(3).setCellValue(baiduMaps.get("two"));
            tempRow.createCell(4).setCellValue(tencentMaps.get("one"));

            tempRow.createCell(6).setCellValue(baiduMaps.get("source"));
            tempRow.createCell(7).setCellValue(tencentMaps.get("source"));
        }

        FileOutputStream fileOutputStream = new FileOutputStream(filepath);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("生产文档完成 !");
    }
}
