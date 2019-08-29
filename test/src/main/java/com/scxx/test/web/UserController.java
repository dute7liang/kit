package com.scxx.test.web;


import java.util.List;

import com.scxx.test.repository.model.TUser;
import com.scxx.test.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author auto
 * @since 2019-08-28
 */
@RestController
@RequestMapping("/tuser")
@Api
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("findAll")
	@ApiOperation("test")
	public List<TUser> find(){
		return userService.testFind();
	}


}

