package com.styzf.lambda.demo;

import cn.hutool.core.util.RandomUtil;
import com.styzf.dome.constant.PrintEnum;
import com.styzf.dome.strategy.LaserPrinterStrategy;
import com.styzf.dome.strategy.PrintDO;
import com.styzf.dome.strategy.SystemStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * 表驱动优化if...else...
 * @author styzf
 * @date 2021/11/21 10:17
 */
public class One {
    
    public static void main(String[] args) {
        One one = new One();
        PrintDO printDO = one.rountPrint(new PrintDO());
        printDO = one.rountPrintByMap(new PrintDO());
    }
    
    private static final Map<PrintEnum, Function<PrintDO, PrintDO>> FUNCTION_MAP = new HashMap<>();
    
    {
        FUNCTION_MAP.put(PrintEnum.SYS_PRINT, this::systemPrint);
        FUNCTION_MAP.put(PrintEnum.LASER_PRINTER_PRINT, this::laserPrint);
        FUNCTION_MAP.put(PrintEnum.HELLO_WORLD_PRINT, this::easyPrint);
        FUNCTION_MAP.put(PrintEnum.PRINTER_PRINT, this::easyPrint);
    }
    
    /**
     * 优化后路由
     * 表驱动
     * @param printDO
     * @return
     */
    public PrintDO rountPrintByMap(PrintDO printDO) {
        int i = RandomUtil.randomInt(PrintEnum.values().length);
        PrintEnum printEnum = PrintEnum.values()[i];
        Function<PrintDO, PrintDO> function = FUNCTION_MAP.get(printEnum);
        if (Objects.isNull(function)) {
            throw new RuntimeException("未找到对应的实现");
        }
        return function.apply(new PrintDO());
    }
    
    /**
     * 普通路由写法
     * @param printDO
     * @return
     */
    public PrintDO rountPrint(PrintDO printDO) {
        int i = RandomUtil.randomInt(PrintEnum.values().length);
        PrintEnum printEnum = PrintEnum.values()[i];
        if (PrintEnum.SYS_PRINT.equals(printEnum)) {
            return systemPrint(printDO);
        } else if (PrintEnum.LASER_PRINTER_PRINT.equals(printEnum)) {
            return laserPrint(printDO);
        } else {
            return easyPrint(printDO);
        }
    }
    
    private PrintDO systemPrint(PrintDO printDO) {
        SystemStrategy printStrategy = new SystemStrategy();
        return printStrategy.print(printDO);
    }
    
    private PrintDO laserPrint(PrintDO printDO) {
        LaserPrinterStrategy printStrategy = new LaserPrinterStrategy();
        return printStrategy.print(printDO);
    }
    
    private PrintDO easyPrint(PrintDO printDO) {
        SystemStrategy printStrategy = new SystemStrategy();
        return printStrategy.print(printDO);
    }
}
