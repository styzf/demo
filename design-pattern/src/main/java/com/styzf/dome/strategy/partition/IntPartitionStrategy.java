package com.styzf.dome.strategy.partition;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 整型自定义数据切分
 * @author styzf
 * @date 2021-01-27
 */
public class IntPartitionStrategy implements PartitionStrategy {
    
    @Override
    public List<Object> partition(Object startStr, Object endStr) {
        BigInteger start = BigInteger.valueOf(Integer.parseInt(startStr.toString()));
        BigInteger end = BigInteger.valueOf(Integer.parseInt(endStr.toString()));
    
        BigInteger subtract = end.subtract(start).add(BigInteger.ONE);
        int step = 1;
        if (subtract.intValue() > PARTITION_NUM) {
            step = BigDecimal.valueOf(subtract.longValue()).
                    divide(BigDecimal.TEN, RoundingMode.HALF_UP).intValue();
        }
    
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < PARTITION_NUM; i++) {
            list.add(start.add(BigInteger.valueOf(step)));
        }
        
        return new ArrayList<>();
    }
}
