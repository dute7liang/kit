package generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 实体生成
 * @author: zl
 * @Date: 2019-8-28 10:54
 */
public class EntityGenerator {

	/**
	 * 模块名称
	 */
	private static String moduleName = "test";

	/**
	 * 模块路径名称，一般和模块名称一致
	 */
	private static String moduleNamePath = "test";

	/**
	 * 表名，可用逗号分隔
	 */
	private static String tableName = "IPO_KIT_TEST";

	/**
	 * 是否覆盖文件
	 */
	private static boolean fileOverride = false;

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/" + moduleNamePath + "/src/main/java");
		gc.setAuthor("auto");
		gc.setOpen(true);
		gc.setFileOverride(fileOverride);
		gc.setDateType(DateType.ONLY_DATE);
		gc.setEntityName("T%s");
		gc.setIdType(IdType.UUID);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:oracle:thin:@//192.168.72.50:1521/ORCL");
		dsc.setDriverName("oracle.jdbc.OracleDriver");
		dsc.setUsername("PALADIN1");
		dsc.setPassword("PALADIN1");
		dsc.setTypeConvert(new ScxxTypeConvert());
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
//		pc.setModuleName(moduleName);
		pc.setParent(null);
		pc.setEntity("com.scxx."+moduleName+".repository.model");
		pc.setMapper("com.scxx."+moduleName+".repository.dao");
		pc.setXml("com.scxx."+moduleName+".repository.dao.mapping");
		pc.setService("TTT");       //本项目没用，生成之后删掉
		pc.setServiceImpl("TTT");   //本项目没用，生成之后删掉
		pc.setController("TTT");    //本项目没用，生成之后删掉
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};

		// 如果模板引擎是 freemarker
//		String templatePath = "/templates/mapper.xml.ftl";
		// 如果模板引擎是 velocity
		// String templatePath = "/templates/mapper.xml.vm";

		// 自定义输出配置
//		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		/*focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
						+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});*/
	/*
	cfg.setFileCreate(new IFileCreate() {
		@Override
		public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
			// 判断自定义文件夹是否需要创建
			checkDir("调用默认方法创建的目录");
			return false;
		}
	});
	*/
//		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();

		// 配置自定义输出模板
		//指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		// templateConfig.setEntity("templates/entity2.java");
		// templateConfig.setService();
		// templateConfig.setController();

		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//		strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类
//		strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
		// 写于父类中的公共字段
		strategy.setSuperEntityColumns("id");
		strategy.setInclude(tableName);
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);
//		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}


}
