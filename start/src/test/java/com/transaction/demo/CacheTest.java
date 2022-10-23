package com.transaction.demo;

import com.transaction.constant.AgeEnum;
import com.transaction.demo.mybatis.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author styzf
 * @date 2021/12/17 21:18
 */
@Slf4j
@SpringBootTest
public class CacheTest {
    
    @Autowired
    private UserInfoService userInfoService;
    
    @Test
    @Transactional
    public void mainTest() {
        UserInfo userInfo1 = userInfoService.getUserInfoById("1");
        log.info("mainTest的结果：{}", userInfo1.getAge().getDesc());
        testOthGet();
        log.info("调用testOthGet的结果：{}", userInfo1.getAge().getDesc());
    }
    
    private void testOthGet() {
        UserInfo userInfo2 = userInfoService.getUserInfoById("1");
        userInfo2.setAge(AgeEnum.TEST_THIRTY);
        log.info("testOthGet的结果：{}", userInfo2.getAge().getDesc());
    }
}
