package com.transaction.constant;

import com.transaction.util.EnumUtil;

/**
 * @author styzf
 * @date 2021/10/27 20:13
 */
public enum AgeEnum implements EnumUtil.BaseEnum<Integer> {
    /**
     * 枚举测试列表
     */
    TEST_EIGHTEEN(18, "永远的18岁"),
    TEST_THIRTY(30, "30岁的老头子"),
    ;
    
    AgeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
    
    private final Integer type;
    
    private final String desc;
    
    public Integer getType() {
        return type;
    }
   
    public String getDesc() {
        return desc;
    }
    
    @Override
    public Integer value() {
        return type;
    }
}
