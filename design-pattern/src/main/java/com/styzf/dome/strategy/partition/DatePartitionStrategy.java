package com.styzf.dome.strategy.partition;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 日期型自定义数据切分
 * @author styzf
 * @date 2021-01-27
 */
public class DatePartitionStrategy implements PartitionStrategy {
    
    @Override
    public List<Object> partition(Object startStr, Object endStr) {
        DateTime start = DateUtil.parse(startStr.toString());
        DateTime end = DateUtil.parse(endStr.toString());
    
        long between = DateUtil.between(start, end, DateUnit.DAY);
        long step = 1;
        if (between > PARTITION_NUM) {
            step = BigDecimal.valueOf(between).
                    divide(BigDecimal.TEN, RoundingMode.HALF_UP).intValue();
        }
    
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < PARTITION_NUM; i++) {
            DateTime offsetEnd = DateUtil.offsetDay(start, BigDecimal.valueOf(i + 1).multiply(BigDecimal.valueOf(step)).intValue());
            list.add(offsetEnd);
        }
    
        return list;
    }
}
