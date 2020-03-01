package com.duteliang.bgs.service.impl;

import com.duteliang.bgs.model.UserAuth;
import com.duteliang.bgs.service.MemberService;
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
