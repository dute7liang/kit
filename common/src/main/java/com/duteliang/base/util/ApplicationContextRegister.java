package com.duteliang.base.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author: zl
 * @Date: 2019-10-10 16:35
 */
@Component
@Lazy(false)
@Slf4j
public class ApplicationContextRegister implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT;

    /**
     * 设置spring上下文  *  * @param applicationContext spring上下文  * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.debug("ApplicationContext registed-->{}", applicationContext);
        APPLICATION_CONTEXT = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    public static Object getBean(String beanName){
        return APPLICATION_CONTEXT.getBean(beanName);
    }

    public static <T> T getBean(Class<T> tClass){
        return APPLICATION_CONTEXT.getBean(tClass);
    }

    public static <T> T getBean(String beanName,Class<T> tClass){
        return APPLICATION_CONTEXT.getBean(beanName,tClass);
    }
}
