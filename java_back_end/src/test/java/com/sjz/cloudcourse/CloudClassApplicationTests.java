package com.sjz.cloudcourse;

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


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;

@SpringBootTest(classes = JavaBookManager.class)

public class CloudClassApplicationTests {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void redisTest() {
        System.out.println("redisTemplate = " + redisTemplate);
        redisTemplate.opsForValue().set("name", "sjz");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }


    @Test
   public void contextLoads() {
        // 需要构建一个 代码自动生成器 对象
        AutoGenerator mpg = new AutoGenerator();
// 配置策略
// 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("Ambition");
        gc.setOpen(false);
        gc.setFileOverride(false); // 是否覆盖
        gc.setServiceName("%sService");// 去Service的I前缀
//        去掉表的前缀

         gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);
//2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://139.9.38.7:3306/cloud_class?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
                dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
//3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("cloudcourse");
        pc.setParent("com.sjz");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);
//4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("ucenter_member"); // 设置要映射的表名
                strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true); // 自动lombok；
        strategy.setLogicDeleteFieldName("del_flag");
         strategy.setTablePrefix("oc_");
// 自动填充配置
        TableFill gmtCreate = new TableFill("CREATED_TIME", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("UPDATED_TIME",
                FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
// 乐观锁
        strategy.setVersionFieldName("REVISION");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true); //

        mpg.setStrategy(strategy);
        mpg.execute(); //执行
    }

}
