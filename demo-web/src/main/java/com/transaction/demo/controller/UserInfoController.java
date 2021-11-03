package com.transaction.demo.controller;

import com.transaction.demo.UserInfoService;
import com.transaction.demo.mybatis.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 测试入口
 * @author styzf
 * @date 2021/8/8 10:17
 */
@RestController
public class UserInfoController {

	@Autowired
	private UserInfoService getUserInfoService;

	@PostMapping("/userinfo")
	public Boolean add(@RequestBody UserInfo userInfo) {
		getUserInfoService.add(userInfo);
		return Boolean.TRUE;
	}
	
	@PostMapping("/this/userinfo")
	public Boolean addByThis(@RequestBody UserInfo userInfo) {
		getUserInfoService.addByThis(userInfo);
		return Boolean.TRUE;
	}
	
	@PostMapping("/async/userinfo")
	public Boolean addAsync(@RequestBody UserInfo userInfo) {
		getUserInfoService.addAsync(userInfo);
		return Boolean.TRUE;
	}
	
	@PostMapping("/autowired/userinfo")
	public Boolean addByAutowired(@RequestBody UserInfo userInfo) {
		getUserInfoService.addByAutowired(userInfo);
		return Boolean.TRUE;
	}

	@PostMapping("/exception/userinfo")
	public Boolean addByException(@RequestBody UserInfo userInfo) throws Exception {
		getUserInfoService.addByException(userInfo);
		return Boolean.TRUE;
	}
}
