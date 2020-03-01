package com.duteliang.web.config;

import com.duteliang.security.config.JwtSecurityConfig;
import com.duteliang.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/1 16:29
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends JwtSecurityConfig {

    @Autowired
    private MemberService memberService;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> memberService.getByUsername(username);
    }
}
