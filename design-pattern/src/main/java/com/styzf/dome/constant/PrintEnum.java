package com.styzf.dome.constant;

import com.transaction.util.EnumUtil;
import lombok.Getter;

/**
 * 打印决策用枚举类
 * @author styzf
 * @date 2021/10/27 20:13
 */
@Getter
public enum PrintEnum implements EnumUtil.BaseEnum<String> {
    /**
     * 枚举列表
     */
    SYS_PRINT("sys", "系统打印"),
    HELLO_WORLD_PRINT("hello_world", "初始化打印"),
    LASER_PRINTER_PRINT("laser_printer", "激光打印"),
    PRINTER_PRINT("printer", "打印机打印");
    
    PrintEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }
    
    private final String type;
    
    private final String desc;
    
    @Override
    public String value() {
        return type;
    }
}
