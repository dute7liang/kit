package com.duteliang.web.service.impl;

import com.duteliang.web.model.UserAuth;
import com.duteliang.web.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/1 16:35
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Override
    public UserAuth getByUsername(String username) {
        UserAuth userAuth = new UserAuth();
        userAuth.setUsername(username);
        userAuth.setPassword(username);
        return userAuth;
    }
}
