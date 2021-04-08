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
        dataSource1.setUrl("jdbc:mysql://127.0.0.1:3306/sharding_order_service0?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第二个数据源
        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://127.0.0.1:3306/sharding_order_service1?useUnicode=true&characterEncoding=utf-8&useSSL=false");
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

        // ============== 配置t_order表规则（规则包含两个部分：分库规则与分表规则），配置订单表既分库又分表，库的分片策略按照user_id，表的分片策略按照order_id
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order","ds${0..1}.t_order_${0..1}");

//        // 指定主键字段
//        orderTableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "order_id"));

        // 配置分库规则
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(
                // 基于用户ID user_id字段值对2取模进行分库路由
                new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));

        // 配置分表规则
        orderTableRuleConfig.setTableShardingStrategyConfig(
                // 基于订单ID order_id字段值对2取模进行分表路由
                new InlineShardingStrategyConfiguration("order_id", "t_order_${order_id % 2}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // ============== 配置order_item表规则，配置订单条目表既分库又分表
        TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration("t_order_item","ds${0..1}.t_order_item_${0..1}");

//        // 指定主键字段
//        orderItemTableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "order_item_id"));

        // 配置分库规则
        orderItemTableRuleConfig.setDatabaseShardingStrategyConfig(
                // 基于用户ID user_id字段值对2取模进行分库路由
                new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));

        // 配置分表规则
        orderItemTableRuleConfig.setTableShardingStrategyConfig(
                // 基于订单ID order_id字段值对2取模进行分表路由
                new InlineShardingStrategyConfiguration("order_id", "t_order_item_${order_id % 2}"));
        shardingRuleConfig.getTableRuleConfigs().add(orderItemTableRuleConfig);

        // 获取数据源对象
        Properties properties = new Properties();
        // 开启显示SQL
        properties.put("sql.show", true);
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, properties);
        return dataSource;
    }

}