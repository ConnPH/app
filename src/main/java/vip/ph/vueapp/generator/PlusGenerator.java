package vip.ph.vueapp.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * @author Just be alive
 * @version 1.0
 * @date 2021-04-24 - 21:12
 */
public class PlusGenerator {
    public static void main(String[] args) {

        //创建代码生成器对象
        AutoGenerator autoGenerator=new AutoGenerator();

        // 1.全局配置
        GlobalConfig globalConfig=new GlobalConfig();

        globalConfig.setActiveRecord(true)//是否支持AR模式
                .setAuthor("Just be alive")//作者
                .setOutputDir("D://MybatisPlus/mybatisplus/src/main/java")//生成路径
                .setOpen(true)//是否打开资源管理器
                .setFileOverride(true)//生成文件覆盖
                .setIdType(IdType.AUTO)//主键策略
                .setServiceName("%sService")//设置生成service接口的名字首字母是否为I
                .setDateType(DateType.ONLY_DATE)
                .setSwagger2(true);
        autoGenerator.setGlobalConfig(globalConfig);

        //2.设置数据源
        DataSourceConfig dsc=new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3308/app?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=UTF-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dsc);


        //3.包的配置
        PackageConfig pc=new PackageConfig();
        //  pc.setModuleName("mybatisplus");//设置模块名
        pc.setParent("vip.ph.vueapp");
        pc.setEntity("model");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        autoGenerator.setPackageInfo(pc);

        //4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("t_user","t_system_log","t_system_exce"); // 设置要映射的表名
        strategy.setNaming(NamingStrategy.underline_to_camel);//设置命名规则，允许驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//设置命名规则，允许列驼峰命名
        strategy.setEntityLombokModel(true); // 自动lombok；
        strategy.setLogicDeleteFieldName("deleted");//设置逻辑删除的名字
        // 自动填充配置
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);//设置自动填充创建时间
        TableFill gmtModified = new TableFill("gmt_modified",FieldFill.INSERT_UPDATE);//设置自动填充修改时间
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        // 乐观锁
        //生成的类名去掉前缀 如yw_sys_user ---> SysUser
        strategy.setTablePrefix("t_");
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);//controller层使用rest风格
        strategy.setControllerMappingHyphenStyle(true); //localhost:8080/hello_id_2
        autoGenerator.setStrategy(strategy);
        autoGenerator.execute();

    }
}
