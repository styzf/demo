package com.styzf.dome.nonstrategy.strategy;

import com.styzf.dome.strategy.PrintDO;

/**
 * 无策略
 * @author styzf
 * @date 2021/10/31 11:09
 */
public class SystemNonStrategy {
    public PrintDO print(PrintDO printDO) {
        System.out.println("系统打印");
        return printDO;
    }
}
