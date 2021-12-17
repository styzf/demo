package com.transaction.demo;



import com.transaction.demo.mybatis.entity.UserInfo;
import org.springframework.ui.Model;


/*
 *
 * @author paida 派哒 zeyu.pzy@alibaba-inc.com
 * @date 2020/10/27
 */


public interface UserInfoService {

    void getUserInfoById(String id, Model model);
    
    UserInfo getUserInfoById(String id);
    
    /**
     * 新增数据
     * @param user
     */
    void add(UserInfo user);
    
    /**
     * 新增数据，通过this调用
     * @param user
     */
    void addByThis(UserInfo user);
    
    /**
     * 新增数据，通过注入调用
     * @param user
     */
    void addByAutowired(UserInfo user);
    
    /**
     * 异常调用
     * @param user
     */
    void addByException(UserInfo user) throws Exception;
    
    /**
     * 异步调用
     * @param user
     */
    void addAsync(UserInfo user);
}
