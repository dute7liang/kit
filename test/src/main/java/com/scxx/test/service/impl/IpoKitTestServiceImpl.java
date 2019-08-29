package com.scxx.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scxx.test.repository.dao.IpoKitTestMapper;
import com.scxx.test.repository.model.TIpoKitTest;
import com.scxx.test.service.IIpoKitTestService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * KIT测试表 服务实现类
 * </p>
 *
 * @author auto
 * @since 2019-08-29
 */
@Service
public class IpoKitTestServiceImpl extends ServiceImpl<IpoKitTestMapper, TIpoKitTest> implements IIpoKitTestService {

}
