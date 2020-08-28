package com.weiun.sharding;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigJavaCode {

    public DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 逻辑表配置
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
        // 关联表配置
        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
        // 全局表配置（广播表）
        shardingRuleConfig.getBroadcastTables().add("t_config");
        // 数据库分片策略，根据user_id取模分片
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "test${user_id % 2}"));
        // 表分片策略，根据order_id按自定义分片策略分片
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new ModuloShardingTableAlgorithm()));
        Properties props = new Properties();
        props.setProperty("sqlShow", "true");
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, props);
    }

    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        // 主键生成策略
        KeyGeneratorConfiguration result = new KeyGeneratorConfiguration("SNOWFLAKE", "order_id");
        return result;
    }

    private TableRuleConfiguration getOrderTableRuleConfiguration() {
        // 逻辑表t_order对应哪些物理表（test*.t_order*）
        TableRuleConfiguration result = new TableRuleConfiguration("t_order", "test${0..1}.t_order${0..1}");
        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        return result;
    }

    private TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        // 逻辑表t_order_item对应哪些物理表（test*.t_order_item*）
        TableRuleConfiguration result = new TableRuleConfiguration("t_order_item", "test${0..1}.t_order_item${0..1}");
        return result;
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("test0", createDataSource("test0"));
        result.put("test1", createDataSource("test1"));
        return result;
    }

    private static final String HOST = "localhost";

    private static final int PORT = 3306;

    private static final String USER_NAME = "root";

    private static final String PASSWORD = "12345678";

    private DataSource createDataSource(final String dataSourceName) {
        HikariDataSource result = new HikariDataSource();
        result.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        result.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8", HOST, PORT, dataSourceName));
        result.setUsername(USER_NAME);
        result.setPassword(PASSWORD);
        return result;
    }

}
