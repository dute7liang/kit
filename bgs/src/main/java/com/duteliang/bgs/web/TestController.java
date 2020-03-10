package com.duteliang.bgs.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zl
 * @Date: 2019-8-28 15:32
 */
@RestController
@Slf4j
public class TestController {



	@GetMapping("/userInfo")
	public String getUserInfo(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.toString();
	}

	@GetMapping("/")
	public String test(){
		return "base/index";
	}

	@GetMapping("/test")
	public String test1(){
		return "index";
	}





}
