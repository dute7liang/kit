package com.scxx.web.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: zl
 * @Date: 2019-8-28 17:20
 */
@Component
@ConfigurationProperties(prefix = "scxx.ipo")
@Data
@ToString
public class IpoBean {

	private String name;

	private Integer age;
}
