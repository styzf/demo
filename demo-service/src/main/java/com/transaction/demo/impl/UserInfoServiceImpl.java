package com.transaction.demo.impl;

import com.transaction.demo.UserInfoService;
import com.transaction.demo.mybatis.entity.UserInfo;
import com.transaction.demo.mybatis.mapper.UserMapper;
import com.transaction.demo.mybatis.util.ApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

/**
 * 事务测试
 * @author styzf
 * @date 2021/8/8 10:17
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    protected UserMapper userMapper;
    
    @Override
    public void getUserInfoById(String id, Model model) {
        //search by id, get UserInfo
        UserInfo user = userMapper.queryUserInfo(id);
        log.info(user.toString());
//        model.addAttribute("name", user.getId())
//                .addAttribute("age", user.getAge())
//                .addAttribute("height", user.getHeight())
//                .addAttribute("weight", user.getWeight());
    }
    
    @Override
    public UserInfo getUserInfoById(String id) {
        return userMapper.queryUserInfo(id);
    }
    
    @Override
    @Transactional
    public void add(UserInfo user) {
        userMapper.insert(user);
        throw new RuntimeException("测试回滚");
    }
    
    @Override
    public void addByThis(UserInfo user) {
        add(user);
    }
    
    @Override
    public void addByAutowired(UserInfo user) {
        UserInfoServiceImpl userInfoService = ApplicationContextHolder.getBean(this.getClass());
        userInfoService.add(user);
    }
    
    @Override
    @Transactional
    public void addByException(UserInfo user) throws Exception {
        userMapper.insert(user);
        throw new Exception("测试回滚");
    }
    
    @Override
    @Async
    @Transactional
    public void addAsync(UserInfo user) {
        userMapper.insert(user);
        throw new RuntimeException("测试回滚");
    }
}
