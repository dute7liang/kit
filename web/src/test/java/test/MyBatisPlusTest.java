package test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scxx.test.repository.dao.IpoKitTestMapper;
import com.scxx.test.repository.model.TIpoKitTest;
import com.scxx.web.ScxxApplication;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: zl
 * @Date: 2019-8-29 12:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ScxxApplication.class})
@Slf4j
public class MyBatisPlusTest {

	@Autowired
	private IpoKitTestMapper ipoKitTestMapper;

	@Test
	public void insert(){
		TIpoKitTest ipoKitTest = new TIpoKitTest();
		ipoKitTest.setThisAge(28L);
		ipoKitTest.setThisName("王西");
		ipoKitTest.setThisDate(new Date());
		ipoKitTest.setThisPrice(new BigDecimal("0.1251"));
		int insert = ipoKitTestMapper.insert(ipoKitTest);
		log.info(insert + "");
	}

	/**
	 * 普通模式的查询
	 * 单表查询基本都能使用，供参考
	 */
	@Test
	public void query(){
		List<TIpoKitTest> list;
		// 查询 this_name like %王% and this_price < 5
		QueryWrapper<TIpoKitTest> qw = new QueryWrapper<>();
		qw.like("THIS_NAME", "王").lt("THIS_PRICE", 5);
//		list = ipoKitTestMapper.selectList(qw);

		// 查询 (this_name like %王% and this_price < 5) or age = 28
		qw = new QueryWrapper<>();
		qw.nested(con -> con.like("THIS_NAME","王").lt("THIS_PRICE",5))
				.eq("THIS_AGE", 28);
		list = ipoKitTestMapper.selectList(qw);


		list.forEach(ipo -> log.info(ipo.toString()));
	}





}
