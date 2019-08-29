package com.scxx.test.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scxx.test.repository.dao.UserMapper;
import com.scxx.test.repository.model.TUser;
import com.scxx.test.service.IUserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto
 * @since 2019-08-28
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, TUser> implements IUserService {

	@Override
	public List<TUser> testFind() {
		return this.list();
	}

}
