package com.scxx.base.config;

import java.util.Date;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import com.scxx.base.exception.ScxxException;
import com.scxx.base.exception.ScxxExceptionEnum;
import com.scxx.base.util.DateUtil;
import com.scxx.base.web.controller.ScxxErrorView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

//@Configuration
public class DefaultWebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Bean("error")
    public ScxxErrorView error() {
        return new ScxxErrorView();
    }

    @PostConstruct
    public void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer =
                (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
        genericConversionService.addConverter(new StringToDateConverter());
    }

    public class StringToDateConverter implements Converter<String, Date> {

        @Override
        public Date convert(String dateString) {

            String patternDate = "\\d{4}-\\d{1,2}-\\d{1,2}";
            String patternTimeMinutes = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}";
            String patternTimeSeconds = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";

            boolean dateFlag = Pattern.matches(patternDate, dateString);
            boolean timeMinutesFlag = Pattern.matches(patternTimeMinutes, dateString);
            boolean timeSecondsFlag = Pattern.matches(patternTimeSeconds, dateString);

            if (dateFlag) {
                return DateUtil.parseDate(dateString);
            } else if (timeMinutesFlag) {
                return DateUtil.parseTimeMinutes(dateString);
            } else if (timeSecondsFlag) {
                return DateUtil.parseTime(dateString);
            } else {
                throw new ScxxException(ScxxExceptionEnum.INVLIDE_DATE_STRING);
            }

        }
    }
}


