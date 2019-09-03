package com.scxx.test.repository.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scxx.test.repository.model.TIpoKitTest;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * KIT测试表 Mapper 接口
 * </p>
 *
 * @author auto
 * @since 2019-08-29
 */
public interface IpoKitTestMapper extends BaseMapper<TIpoKitTest> {


	void findByCustom(
			@Param(value = "name") String name,
			@Param(value = "age") String age);


	List<TIpoKitTest> findByCustom2(@Param("ipoKitTest") TIpoKitTest ipoKitTest);

	List<TIpoKitTest> findAllByThisAge(@Param("thisAge")Long thisAge);


}
