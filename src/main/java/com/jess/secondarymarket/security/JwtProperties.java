package com.jess.secondarymarket.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 用户注册登录
 * @author Jess
 * @date 2020/7/31 9:23
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;

    private String expiration;

    private String tokenStart;

    private String tokenName;

    /**
     * 添加用户默认密码
     */
    private String defaultPassword;
}
