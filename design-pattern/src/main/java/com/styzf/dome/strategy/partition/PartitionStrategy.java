package com.styzf.dome.strategy.partition;

import java.util.List;

/**
 * 策略模式处理
 * 自定义数据切分
 * @author styzf
 * @date 2021-01-27
 */
public interface PartitionStrategy {
    public final static int PARTITION_NUM = 10;
    /**
     * 切分数据的方法
     * @param startStr 前端传参，切割后的开始的数据
     * @param endStr 前端传参，切割后的结束的数据
     * @return 切割好后的数据列表
     */
    List<Object> partition(Object startStr, Object endStr);
}
