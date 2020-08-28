package com.weiun.sharding;

import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 全局表测试
 */
public class Test001 {

    @Test
    public void insert() throws Exception {
        ConfigJavaCode configJavaCode = new ConfigJavaCode();
        DataSource dataSource = configJavaCode.getShardingDataSource();
        Connection connection = dataSource.getConnection();
        String sql = "insert t_config(id) value(123457)";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    @Test
    public void delete() throws Exception {
        ConfigJavaCode configJavaCode = new ConfigJavaCode();
        DataSource dataSource = configJavaCode.getShardingDataSource();
        Connection connection = dataSource.getConnection();
        String sql = "delete from t_config where id = 123457";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    @Test
    public void update() throws Exception {
        ConfigJavaCode configJavaCode = new ConfigJavaCode();
        DataSource dataSource = configJavaCode.getShardingDataSource();
        Connection connection = dataSource.getConnection();
        String sql = "update t_config set id = id + 1 where id = 123456";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    @Test
    public void query() throws Exception {
        ConfigJavaCode configJavaCode = new ConfigJavaCode();
        DataSource dataSource = configJavaCode.getShardingDataSource();
        Connection connection = dataSource.getConnection();
        String sql = "select * from t_config";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            System.out.println("id:" + id);
        }
    }

}