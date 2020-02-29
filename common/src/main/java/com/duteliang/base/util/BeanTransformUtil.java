package com.duteliang.base.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: zl
 * @Date: 2019-10-17 20:00
 */
@Slf4j
public class BeanTransformUtil {

    public static <T> T copyBean(Object object, Class<T> clazz)  {
        if(null == object){
            return null;
        }
        T value;
        try {
            value = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("转换异常!",e);
            return null;
        }
        BeanUtils.copyProperties(object, value);
        return value;
    }

    public static <T> List<T> copyBeans(Collection<?> objects, Class<T> clazz) {
        List<T> values = new ArrayList<T>();
        if (CollectionUtils.isEmpty(objects)) {
            return values;
        }
        T value;
        for (Object object : objects) {
            try {
                value = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("转换异常!",e);
                return null;
            }
            BeanUtils.copyProperties(object, value);
            values.add(value);
        }
        return values;
    }

}
