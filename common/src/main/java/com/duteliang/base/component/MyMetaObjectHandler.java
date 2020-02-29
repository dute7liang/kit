package com.duteliang.base.component;

import java.util.Date;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 自动填充功能，用于自动记录表的新增和修改时间
 * @author: zl
 * @Date: 2019-8-29 09:28
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {


	@Override
	public void insertFill(MetaObject metaObject) {
		Object createTime = this.getFieldValByName("createTime", metaObject);
		if(createTime == null){
			this.setInsertFieldValByName("createTime",new Date(),metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		Object updateTime = this.getFieldValByName("updateTime", metaObject);
		if(updateTime == null){
			this.setUpdateFieldValByName("updateTime",new Date(),metaObject);
		}
	}
}
