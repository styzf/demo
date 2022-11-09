package com.styzf.util;

import cn.afterturn.easypoi.word.WordExportUtil;
import cn.hutool.core.lang.Assert;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author styzf
 * @date 2022/11/9 23:55
 */
public class WordUtil {
    
    public static void main(String[] args) throws Exception {
        String templatePath = "template\\test.docx";
        String temDir = "tem";
        String fileName = "styzf.docx";
        Map<String, Object> params = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> obj1 = new HashMap<>();
        obj1.put("id", "a1");
        obj1.put("name", "a0");
        Map<String, Object> obj2 = new HashMap<>();
        obj2.put("id", "a2");
        obj2.put("name", "a111");
        Map<String, Object> obj3 = new HashMap<>();
        obj3.put("id", "a3");
        obj3.put("name", "a0+6666");
        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        params.put("name", "测试替换");
        
        params.put("maplist", list);
        exportWord(templatePath, temDir, fileName, params);
    }
    
    /**
     * 生成word
     *
     * @param templatePath
     * @param temDir
     * @param fileName
     * @param params
     */
    public static void exportWord(String templatePath, String temDir, String fileName, Map<String, Object> params) {
        Assert.notNull(templatePath, "模板路径不能为空");
        Assert.notNull(temDir, "临时文件路径不能为空");
        Assert.notNull(fileName, "导出文件名不能为空");
        Assert.isTrue(fileName.endsWith(".docx"), "word导出请使用docx格式");
        if (!temDir.endsWith("/")) {
            temDir = temDir + File.separator;
        }
        File dir = new File(temDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(templatePath, params);
            String tmpPath = temDir + fileName;
            FileOutputStream fos = new FileOutputStream(tmpPath);
            doc.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


