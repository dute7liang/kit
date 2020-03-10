package com.duteliang.bgs.web;

import com.duteliang.base.web.tips.CommonResult;
import com.duteliang.bgs.service.MemberService;
import com.duteliang.security.config.JwtProperty;
import com.duteliang.security.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 主要的权限控制器
 * 包括：注册，登陆，登出，获取验证码，刷新token
 * <br/>
 * author: zl
 * Date: 2020/3/1 22:31
 */
@RestController
@Slf4j
public class UserAuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperty jwtProperty;

    @GetMapping("/login")
    public CommonResult login(String username, String password){
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

    @GetMapping("/refreshToken")
    public CommonResult refreshToken(HttpServletRequest request){
        String token = request.getHeader(jwtProperty.getTokenHeader());
        String refreshToken = jwtTokenUtil.refreshHeadToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", jwtProperty.getTokenHead());
        return CommonResult.success(tokenMap);
    }
}
