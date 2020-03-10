package test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duteliang.test.repository.dao.IpoKitTestMapper;
import com.duteliang.test.repository.model.TIpoKitTest;
import com.duteliang.web.WebApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: zl
 * @Date: 2019-8-29 12:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
@Slf4j
public class MyBatisPlusTest {

	@Autowired
	private IpoKitTestMapper ipoKitTestMapper;

	/**
	 * 插入数据
	 */
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
//		list = ipoKitTestMapper.selectList(qw);

		// 对指定查询固定的字段
		// 1. 固定字段
		// 2. 排除字段
		qw = new QueryWrapper<>();
		// 只查询name和age
//		qw.select("this_name","this_age");
		// 排除name和age
		qw.select(TIpoKitTest.class,info -> !info.getColumn().equals("THIS_AGE") && !info.getColumn().equals("THIS_NAME"));//
		list = ipoKitTestMapper.selectList(qw);



		list.forEach(ipo -> log.info(ipo.toString()));
	}

	/**
	 * 利用lambda表达式来查询，实际和上面的查询一模一样的效果，只是换了一个写法
	 */
	@Test
	public void queryLambda() {
		// 查询 this_name like %王% and this_price < 5
		List<TIpoKitTest> list;
		list = ipoKitTestMapper.selectList(Wrappers.<TIpoKitTest>lambdaQuery()
				.like(TIpoKitTest::getThisName, "王").lt(TIpoKitTest::getThisPrice, 5));

		// 查询 (this_name like %王% and this_price < 5) or age = 28
		list = ipoKitTestMapper.selectList(Wrappers.<TIpoKitTest>lambdaQuery().nested(con ->
				con.like(TIpoKitTest::getThisName, "王").lt(TIpoKitTest::getThisPrice, 5))
				.or().eq(TIpoKitTest::getThisAge,"28"));

		// 对指定查询固定的字段
		list = ipoKitTestMapper.selectList(Wrappers.<TIpoKitTest>lambdaQuery()
				.select(TIpoKitTest.class,info -> !info.getProperty().equals("thisAge")));


		list.forEach(ipo -> log.info(ipo.toString()));
	}

	/**
	 * update示例
	 */
	@Test
	public void update(){
		UpdateWrapper<TIpoKitTest> uw = new UpdateWrapper<>();
		TIpoKitTest test = new TIpoKitTest();
		uw.set("this_age", "").set("this_name",null).eq("this_name","张三");
		/**
		 * 1. 会自动更新test里面非空字段
		 * 2. 对于UpdateWrapper里面set的字段也会更新。 但是不能entity有重复
		 */
		int update = ipoKitTestMapper.update(test, uw);
		log.info(test.toString());

	}

	/**
	 * 分页示例
	 */
	@Test
	public void pageQuery(){
		Page<TIpoKitTest> page = new Page<>();
		// 查询第2页，一页2行数据，会自动count
		page.setSize(2);
		page.setCurrent(2);
		// 查询第2页，一页2行数据，不会自动count,有些场景不需要总页数，比如自动根据滚动条动态加载
		page.setSearchCount(false);
		IPage<TIpoKitTest> testIPage = ipoKitTestMapper.selectPage(page, Wrappers.lambdaQuery());

		log.info("总页数={}",page.getTotal());
		log.info("当前页={}",page.getCurrent());
		log.info("每页数={}",page.getSize());
		testIPage.getRecords().forEach(info -> log.info(info.toString()));

	}

	/**
	 * 通过xml查询数据库
	 */
	@Test
	public void testXml(){
		List<TIpoKitTest> test = ipoKitTestMapper.findAllByThisAge(28L);
//		ipoKitTestMapper.findByCustom("name", "222");
		test.forEach(t -> log.info(t.toString()));
	}






}
