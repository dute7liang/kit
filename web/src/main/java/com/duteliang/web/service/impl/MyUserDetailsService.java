package com.duteliang.web.service.impl;

import com.duteliang.web.model.UserAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/1 23:19
 */
@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = new UserAuth();
        userAuth.setName(username);
        userAuth.setPassword(username);
        return userAuth;
    }
}
