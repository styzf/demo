package com.styzf.dome.strategy;

import com.styzf.dome.constant.PrintEnum;
import com.styzf.dome.strategy.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 打印管理器
 * @author styzf
 * @date 2021/10/31 11:48
 */
public class PrintManagement {
    private final static Map<PrintEnum, BasePrintStrategy> PRINT_STRATEGY_MAP = new HashMap<>();
    
    static {
        PRINT_STRATEGY_MAP.put(PrintEnum.SYS_PRINT, new SystemStrategy());
        PRINT_STRATEGY_MAP.put(PrintEnum.HELLO_WORLD_PRINT, new HelloWorldStrategy());
        PRINT_STRATEGY_MAP.put(PrintEnum.PRINTER_PRINT, new PrinterStrategy());
        PRINT_STRATEGY_MAP.put(PrintEnum.LASER_PRINTER_PRINT, new LaserPrinterStrategy());
    }
    
    public static void print(PrintEnum printEnum) {
        BasePrintStrategy printStrategy = getPrintStrategy(printEnum);
        printStrategy.print(new PrintDO());
    }
    
    public static BasePrintStrategy getPrintStrategy(PrintEnum printEnum) {
        BasePrintStrategy basePrintStrategy = PRINT_STRATEGY_MAP.get(printEnum);
        if (Objects.isNull(basePrintStrategy)) {
            throw new RuntimeException(printEnum + "无该决策实现");
        }
        
        return basePrintStrategy;
    }
}
