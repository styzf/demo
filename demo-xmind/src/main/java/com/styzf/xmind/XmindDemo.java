package com.styzf.xmind;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.system.SystemUtil;
import org.assertj.core.util.Lists;
import org.xmind.core.*;
import org.xmind.core.style.IStyle;
import org.xmind.core.style.IStyleSheet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author styzf
 * @date 2021/11/12 0:15
 */
public class XmindDemo {
    
    /**
     * 当前类路径
     */
    public static final String CLASS_PATH = XmindDemo.class.getResource("/").getPath();
    /**
     * 文件分隔符
     */
    public static final String FILE_SEPARATOR = SystemUtil.getOsInfo().getFileSeparator();
    
    public static void main(String[] args) throws IOException, CoreException {
        // 读取目录
        String bookName = "一本书读懂24种互联网思维";
        String path = CLASS_PATH + bookName + ".txt";
        System.out.println(path);
        List<String> contents = FileUtil.readLines(CLASS_PATH + bookName + ".txt", "utf-8");
        // 创建思维导图的工作空间
        IWorkbookBuilder workbookBuilder = Core.getWorkbookBuilder();
        IWorkbook workbook = workbookBuilder.createWorkbook();
        // 获取样式
        IStyleSheet styleSheet = workbook.getStyleSheet();
        IStyle style = styleSheet.createStyle(IStyle.TOPIC);
        styleSheet.addStyle(style, IStyleSheet.NORMAL_STYLES);
        style.setProperty("color", "0x66CCFF");
        String styleId = style.getId();
        // 获得默认sheet
        ISheet primarySheet = workbook.getPrimarySheet();
        // 获得根主题
        ITopic rootTopic = primarySheet.getRootTopic();
        // 设置根主题的标题
        rootTopic.setTitleText(bookName);
        // 章节 topic 的列表
        ArrayList<ITopic> chapterTopics = Lists.newArrayList();
        for (String content : contents) {
            // 如果是数字开头为章节名称
            if (ReUtil.isMatch("^[1-24].*?", content)) {
                // 创建章节节点
                ITopic topic = workbook.createTopic();
                topic.setStyleId(styleId);
                topic.setTitleText(content);
                chapterTopics.add(topic);
            } else {
                if (chapterTopics.size() == 0) {
                    continue;
                }
                // 创建小节节点
                ITopic topic = workbook.createTopic();
                topic.setStyleId(styleId);
                topic.setTitleText(content);
                chapterTopics.get(chapterTopics.size() - 1).add(topic, ITopic.ATTACHED);
            }
        }
        // 把章节节点添加到要节点上
        chapterTopics.forEach(it -> rootTopic.add(it, ITopic.ATTACHED));
        // 保存
        workbook.save(CLASS_PATH + FILE_SEPARATOR + bookName + ".xmind");
        String xmindPath = CLASS_PATH + FILE_SEPARATOR + bookName + ".xmind";
        System.out.println("file:" + xmindPath.replaceFirst("/","")
                .replaceFirst("\\\\","").replaceAll("/","\\\\"));
    }
    
}
