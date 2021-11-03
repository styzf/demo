package com.transaction.demo;

import com.styzf.dome.constant.PrintEnum;
import com.styzf.dome.strategy.PrintManagement;
import com.styzf.dome.nonstrategy.strategy.HelloWorldNonStrategy;
import com.styzf.dome.nonstrategy.strategy.PrinterNonStrategy;
import com.styzf.dome.nonstrategy.strategy.SystemNonStrategy;
import com.styzf.dome.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

/**
 * 策略模式的实现，策略模式与工厂模式配合实现优化
 * @author styzf
 * @date 2021/10/31 12:03
 */
@Slf4j
@SpringBootTest
public class StrategyTest {
    
    /**
     * 主测试入口
     */
    @Test
    public void mainTest() {
        PrintEnum[] values = PrintEnum.values();
        Random random = new Random();
        int i = random.nextInt(values.length);
        nonStrategy(values[i], new PrintDO());
        strategy(values[i], new PrintDO());
        strategyAndFactory(values[i]);
    }
    
    @Test
    public void testSpeed() {
        PrintEnum[] values = PrintEnum.values();
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            int index = random.nextInt(values.length);
            nonStrategy(values[index], new PrintDO());
        }
        long nonStrategyEnd = System.currentTimeMillis();
        log.warn("非策略模式时间：[{}]", nonStrategyEnd - start);
//        for (int i = 0; i < 100000; i++) {
//            int index = random.nextInt(values.length);
//            strategy(values[index], new PrintDO());
//        }
//        long strategyEnd = System.currentTimeMillis();
//        log.warn("策略模式时间：[{}]", strategyEnd - nonStrategyEnd);
//        for (int i = 0; i < 100000; i++) {
//            int index = random.nextInt(values.length);
//            strategyAndFactory(values[index], new PrintDO());
//        }
//        long strategyAndFactoryEnd = System.currentTimeMillis();
//        log.warn("策略模式+工厂模式时间：[{}]", strategyAndFactoryEnd - strategyEnd);
    }
    
    /**
     * 无策略模式实现
     * @param printEnum
     * @param printDO
     */
    private void nonStrategy(PrintEnum printEnum, PrintDO printDO) {
//        new SystemNonStrategy().print(printDO);
        new PrinterNonStrategy().print(printDO);
    }

    /**
     * 无策略模式实现
     * @param printEnum
     * @param printDO
     */
    private void selectNonStrategy(PrintEnum printEnum, PrintDO printDO) {
        if (PrintEnum.SYS_PRINT.equals(printEnum)) {
            new SystemNonStrategy().print(printDO);
        } else if (PrintEnum.HELLO_WORLD_PRINT.equals(printEnum)) {
            new HelloWorldNonStrategy().print();
        } else if (PrintEnum.PRINTER_PRINT.equals(printEnum)) {
            new PrinterNonStrategy().print(printDO);
        }
    }
    
    /**
     * 策略模式实现
     * @param printEnum
     * @param printDO
     */
    private void strategy(PrintEnum printEnum, PrintDO printDO) {
        if (PrintEnum.SYS_PRINT.equals(printEnum)) {
            new SystemStrategy().print(printDO);
        } else if (PrintEnum.HELLO_WORLD_PRINT.equals(printEnum)) {
            new HelloWorldStrategy().print(printDO);
        } else if (PrintEnum.PRINTER_PRINT.equals(printEnum)) {
            new PrinterStrategy().print(printDO);
        }
    }
    
    /**
     * 策略模式
     * @param printEnum
     */
    private void strategyAndFactory(PrintEnum printEnum) {
        PrintManagement.print(printEnum);
    }
}
