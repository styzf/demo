package com.styzf.dome.strategy;

/**
 * 具体策略
 * @author styzf
 * @date 2021/10/31 11:09
 */
public class SystemStrategy implements BasePrintStrategy {
    @Override
    public PrintDO print(PrintDO printDO) {
        System.out.println("系统打印");
        return printDO;
    }
}
