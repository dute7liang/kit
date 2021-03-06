package com.duteliang.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 默认的一些配置
 *
 * @author fengshuonan
 * @date 2018-01-07 12:33
 */
@Configuration
@PropertySource("classpath:/default-config.properties")
public class DefaultProperties {


}
