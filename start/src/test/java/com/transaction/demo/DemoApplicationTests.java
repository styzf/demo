package com.transaction.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    
    @Autowired
    private UserInfoService userInfoService;
    
    @Test
    void contextLoads() {
        userInfoService.getUserInfoById("1",null);
    }

}
