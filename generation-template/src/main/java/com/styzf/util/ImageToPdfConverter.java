package com.styzf.util;

import cn.afterturn.easypoi.pdf.PdfExportUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author styzf
 * @date 2023/10/17 22:32
 */
public class ImageToPdfConverter {
    public static void main(String[] args) {
        String imagePath = "D:\\test.jpg";
        String pdfPath = "D:\\test.pdf";
        
        try {
            // 读取图片并添加到 PDF 文档中
            Image image = Image.getInstance(imagePath);
            float height = image.getHeight();
            float width = image.getWidth();
            int percent = getPercent(height, width);
            image.setAlignment(Image.ALIGN_LEFT);
            image.scalePercent(percent);
            // 创建 PDF 文档对象
            Rectangle pageSize = new Rectangle(width * (percent + 5) / 100, height * (percent + 5) / 100);
            pageSize.rotate();
            Document document = new Document(pageSize);
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();
            
            document.add(image);
            
            // 关闭文档
            document.close();
            
            System.out.println("图片转 PDF 完成！");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 等比压缩，获取压缩百分比
     *
     * @param height 图片的高度
     * @param weight 图片的宽度
     * @return 压缩百分比
     */
    private static int getPercent(float height, float weight) {
        float percent = 0.0F;
        if (height > weight) {
            percent = PageSize.A4.getHeight() / height * 100;
        } else {
            percent = PageSize.A4.getWidth() / weight * 100;
        }
        return Math.round(percent);
    }
}
