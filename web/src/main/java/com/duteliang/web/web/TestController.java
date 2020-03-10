package com.duteliang.web.web;

import com.duteliang.security.config.JwtProperty;
import com.duteliang.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zl
 * @Date: 2019-8-28 15:32
 */
@Controller
@Slf4j
public class TestController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtProperty jwtProperty;

	@GetMapping("/userInfo")
	@ResponseBody
	public String getUserInfo(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.toString();
	}

	@GetMapping("/")
	public String test(){
		return "base/index";
	}

	@GetMapping("/test")
	@ResponseBody
	public String test1(){
		return "index";
	}

}
