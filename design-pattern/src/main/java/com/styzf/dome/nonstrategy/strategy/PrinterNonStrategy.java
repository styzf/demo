package com.styzf.dome.nonstrategy.strategy;

import com.styzf.dome.strategy.PrintDO;

/**
 * 无策略
 * @author styzf
 * @date 2021/10/31 11:09
 */
public class PrinterNonStrategy {
    public PrintDO print(PrintDO printDO) {
        System.out.println("假装我有打印机");
        return printDO;
    }
}
