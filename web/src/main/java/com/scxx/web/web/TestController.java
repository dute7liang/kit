package com.scxx.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: zl
 * @Date: 2019-8-28 15:32
 */
@Controller
public class TestController {

	@GetMapping("/")
	public String test(){
		return "base/index";
	}



}
