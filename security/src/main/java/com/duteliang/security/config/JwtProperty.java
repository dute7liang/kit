package com.duteliang.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <br/>
 * author: zl
 * Date: 2020/3/1 16:10
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.jwt")
@Component
public class JwtProperty {

    /**
     * JWT存储的请求头
     */
    private String tokenHeader = "Authorization";

    /**
     * JWT负载中拿到开头
     */
    private String tokenHead = "Bearer";

    /**
     * JWT加解密使用的密钥
     */
    private String secret = "duteliang-kit";

    /**
     * JWT的超期限时间(60*60*24)
     */
    private Long expiration = 604800L;

}
