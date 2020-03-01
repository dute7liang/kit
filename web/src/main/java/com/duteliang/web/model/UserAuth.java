package com.duteliang.web.model;

import com.jayway.jsonpath.Criteria;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户校验的封装类
 * <br/>
 * author: zl
 * Date: 2020/3/1 15:10
 */
@Setter
@Getter
public class UserAuth implements UserDetails {

    private String username;
    private String password;
    private String name = "我是自定义的数据";
    private List<Criteria> oredCriteria;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
