package com.duteliang.web.web;

import com.duteliang.base.web.tips.CommonResult;
import com.duteliang.security.config.JwtProperty;
import com.duteliang.security.util.JwtTokenUtil;
import com.duteliang.web.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
	private MemberService memberService;

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

	@GetMapping("/login")
	@ResponseBody
	public CommonResult login(String username,String password){
		String token = null;
		try {
			UserDetails userDetails = memberService.getByUsername(username);
//			if(!passwordEncoder.matches(password,userDetails.getPassword())){
//				throw new BadCredentialsException("密码不正确");
//			}
			if(!password.equals(userDetails.getPassword())){
				throw new BadCredentialsException("密码不正确");
			}

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
		}catch (Exception e){
			log.warn("登录异常:{}", e.getMessage());
		}
		if (token == null) {
			return CommonResult.validateFailed("用户名或密码错误");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", jwtProperty.getTokenHead());
		return CommonResult.success(tokenMap);

	}



}
