package com.duteliang.bgs.service;


import com.duteliang.bgs.model.UserAuth;

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
