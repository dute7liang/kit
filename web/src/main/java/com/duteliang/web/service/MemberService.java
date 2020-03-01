package com.duteliang.web.service;

import com.duteliang.web.model.UserAuth;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/1 16:31
 */
public interface MemberService {

    /**
     * 根据用户名获取用户
     */
    UserAuth getByUsername(String username);


}
