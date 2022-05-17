package com.example.demo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author luQuan
 * @version 1.0
 * @date 2021/6/7 18:36
 * mybatis-plus 代码生成器
 */
public class CodeGenerator {

    private static String projectPath = System.getProperty("user.dir");
    private static String DriverName = "com.mysql.cj.jdbc.Driver";
    //private static String Url = "jdbc:mysql://49.233.123.122:3306/iot?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static String Url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    private static String username = "root";
    private static String password = "123456";
    private static String MICROSERVICE = "";

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 全局配置
     *
     * @return
     */
    public static GlobalConfig getGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
//        MICROSERVICE = scanner("微服务名");
//        gc.setOutputDir(projectPath + "/" + MICROSERVICE + "/src/main/java");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("luQuan");
        gc.setOpen(false);
        gc.setFileOverride(false); //如果存在文件是否覆盖
        gc.setServiceName("%sService"); //去掉service前面的I前缀
        gc.setIdType(IdType.AUTO); //设置主键策略为自增
        gc.setDateType(DateType.ONLY_DATE); //设置日期格式
        //gc.setSwagger2(true); // 实体属性 Swagger2 注解
        return gc;
    }

    /**
     * 数据源配置
     *
     * @return
     */
    public static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName(DriverName);
        dsc.setUrl(Url);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setDbType(DbType.MYSQL);
        return dsc;
    }

    /**
     * 包配置
     *
     * @return
     */
    public static PackageConfig getPackageConfig() {
        PackageConfig pc = new PackageConfig();
        //将来代码会生成于com.isoftStone.manage.user的目录下
        //自定义输入模块名称
//        pc.setModuleName(scanner("模块名称"));
        pc.setParent("com.example.demo");
        pc.setEntity("entity");
        pc.setMapper("com.example.demo/mapper");
        pc.setController("controller");
        pc.setService("service");
        return pc;
    }

    /**
     * 策略配置
     *
     * @return
     */
    public static StrategyConfig getStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        //下划线转驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //列名下划线转驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//      strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        //自动支持Lombok注解
        strategy.setEntityLombokModel(true);
        //设置实体类逻辑删除字段
        strategy.setLogicDeleteFieldName("deleted");
        //设置自动填充字段
        TableFill createDate = new TableFill("oper_time", FieldFill.INSERT);
//        TableFill modifyDate = new TableFill("modify_date", FieldFill.INSERT_UPDATE);
        List<TableFill> list = new ArrayList<>();
        list.add(createDate);
//        list.add(modifyDate);
        strategy.setTableFillList(list);
        //设置乐观锁
        strategy.setVersionFieldName("version");
        //设置下划线命名 localhost:8080/hello_id_2
        strategy.setControllerMappingHyphenStyle(true);
        //设置restful的驼峰命名
        strategy.setRestControllerStyle(true);
        strategy.setSuperEntityClass(BaseDO.class);
        // 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        //strategy.setSuperEntityColumns("id");
        //strategy.setInclude("user","product"); 设置只映射生成两张表：user表和product表
        //配置生成的表由控制台输入
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        return strategy;
    }

    /***
     * 入口L
     * @param args
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 1.全局配置
        GlobalConfig gc = CodeGenerator.getGlobalConfig();
        mpg.setGlobalConfig(gc);
        // 2.数据源配置
        DataSourceConfig dsc = CodeGenerator.getDataSourceConfig();
        mpg.setDataSource(dsc);
        // 3.包配置
        PackageConfig pc = CodeGenerator.getPackageConfig();
        mpg.setPackageInfo(pc);
        // 4.策略配置
        StrategyConfig strategy = CodeGenerator.getStrategyConfig();
        mpg.setStrategy(strategy);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置,自定义配置会被优先输出
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/" + MICROSERVICE + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板,不在mapper中新建xml目录并生成xml文件
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
