package com.weiun.sharding;

import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 分片表测试
 */
public class Test002 {

    @Test
    public void insert() throws Exception {
        ConfigJavaCode configJavaCode = new ConfigJavaCode();
        DataSource dataSource = configJavaCode.getShardingDataSource();
        Connection connection = dataSource.getConnection();
        String sql = "insert into t_order(order_id,user_id) values(1,1),(2,2),(3,3),(4,4),(5,6),(6,7),(7,8),(8,9)";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    @Test
    public void insertItem() throws Exception {
        ConfigJavaCode configJavaCode = new ConfigJavaCode();
        DataSource dataSource = configJavaCode.getShardingDataSource();
        Connection connection = dataSource.getConnection();
        String sql = "insert into t_order_item(order_id,user_id,order_item_id) values(1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,6,5),(6,7,6),(7,8,7),(8,9,8)";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    @Test
    public void delete() throws Exception {
        ConfigJavaCode configJavaCode = new ConfigJavaCode();
        DataSource dataSource = configJavaCode.getShardingDataSource();
        Connection connection = dataSource.getConnection();
        String sql = "delete from t_order where order_id = 1";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    @Test
    public void update() throws Exception {
        ConfigJavaCode configJavaCode = new ConfigJavaCode();
        DataSource dataSource = configJavaCode.getShardingDataSource();
        Connection connection = dataSource.getConnection();
        // 不能更新分片规则字段
        String sql = "update t_order set user_id = 4 where order_id = 1";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    @Test
    public void query() throws Exception {
        ConfigJavaCode configJavaCode = new ConfigJavaCode();
        DataSource dataSource = configJavaCode.getShardingDataSource();
        Connection connection = dataSource.getConnection();
        String sql = "select * from t_order";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            long id = resultSet.getLong("order_id");
            long userId = resultSet.getLong("user_id");
            System.out.println("order_id:" + id + " user_id:" + userId);
        }
    }

    @Test
    public void queryJoin() throws Exception {
        ConfigJavaCode configJavaCode = new ConfigJavaCode();
        DataSource dataSource = configJavaCode.getShardingDataSource();
        Connection connection = dataSource.getConnection();
        String sql = "select o.*,i.order_item_id from t_order o left join t_order_item i on i.order_id = o.order_id";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            long id = resultSet.getLong("order_id");
            long userId = resultSet.getLong("user_id");
            long orderItemId = resultSet.getLong("order_item_id");
            System.out.println("order_id:" + id + " user_id:" + userId + " order_item_id:" + orderItemId);
        }
    }

}