package com.scxx.base.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 全局的控制器
 *
 * @author zl
 * @date 2019年8月28日14:05:15
 */
@RequestMapping("/global")
public class GlobalController {

    /**
     * 跳转到404页面
     *
     * @author fengshuonan
     */
    @RequestMapping(path = "/error")
    public String errorPage() {
        return "error/404";
    }

}
