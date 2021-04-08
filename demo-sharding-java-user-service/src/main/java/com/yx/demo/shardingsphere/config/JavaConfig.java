package com.yx.demo.shardingsphere.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author yangxi
 * @version 1.0
 */
@Configuration
public class JavaConfig {

    @Bean
    public DataSource dataSource() throws SQLException {

        /**
         * 1、创建数据源集合：dataSourceMap
         *
         * 2、创建分片规则：shardingRuleConfig
         *
         * 3、创建配置的属性（是否显示sql等） properties
         *
         */

        // 1、创建数据源集合：dataSourceMap
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://127.0.0.1:3306/sharding_user_service0?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第二个数据源
        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://127.0.0.1:3306/sharding_user_service1?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSource2.setUsername("root");
        dataSource2.setPassword("root");
        dataSourceMap.put("ds1", dataSource2);


        // 2、创建分片规则：shardingRuleConfig

        /**
         * 需要构建表规则
         * 1、指定逻辑表
         * 2、配置实际节点
         * 3、指定主键字段
         * 4、分库和分表的规则
         */

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();

        // ============== 配置t_user表规则（配置用户表只分库不分表）
        TableRuleConfiguration userTableRuleConfig = new TableRuleConfiguration("t_user","ds${0..1}.t_user");

        // 指定主键字段（不设置的，默认是会给主键ID字段采用SNOWFLAKE进行赋值）
//        userTableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "user_id"));

        // 配置分库规则
        userTableRuleConfig.setDatabaseShardingStrategyConfig(
                // 基于用户ID user_id字段值对2取模进行分库路由
                new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        shardingRuleConfig.getTableRuleConfigs().add(userTableRuleConfig);

        // 获取数据源对象
        Properties properties = new Properties();
        // 开启显示SQL
        properties.put("sql.show", true);
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, properties);
        return dataSource;
    }

}