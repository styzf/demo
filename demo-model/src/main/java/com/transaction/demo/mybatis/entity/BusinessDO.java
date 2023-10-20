package com.transaction.demo.mybatis.entity;

import com.transaction.annotation.LazyData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author styzf
 * @date 2023/2/8 21:51
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class BusinessDO {
    private UserInfo userInfo;
    
    @LazyData("userInfo")
    public UserInfo getUserInfo() {
        return userInfo;
    }
    
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
