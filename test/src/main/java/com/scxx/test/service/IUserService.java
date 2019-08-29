package com.scxx.test.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scxx.test.repository.model.TUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto
 * @since 2019-08-28
 */
public interface IUserService extends IService<TUser> {

	List<TUser> testFind();

}
