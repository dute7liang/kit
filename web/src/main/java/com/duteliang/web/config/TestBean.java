package com.duteliang.web.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: zl
 * @Date: 2019-8-28 17:20
 */
@Component
@ConfigurationProperties(prefix = "scxx.test")
@Data
@ToString
public class TestBean {

	private String name;

	private Integer age;
}
