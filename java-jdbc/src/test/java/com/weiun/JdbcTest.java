package com.weiun;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.weiun.jdbc.JdbcUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

/**
 * @author Administrator
 * @createTime 2019/6/29 20:39
 * @description JDBC测试
 */
public class JdbcTest {

    private Connection connection;

    @Before
    public void before() throws Exception {
        // 构建H2内存数据库
        String url = "jdbc:hsqldb:mem:test";
        String user = "SA";
        String password = "";
        connection = JdbcUtils.getConnection(url, user, password);
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        scriptRunner.setLogWriter(null);
        scriptRunner.runScript(Resources.getResourceAsReader("db_init.sql"));
        System.out.println("==============================");
        System.out.println("=====  DbCreateSuccess  ======");
        System.out.println("==============================");
    }

    @After
    public void after() throws SQLException {
        if (connection != null && !connection.isClosed())
            connection.close();
    }

    @Test
    public void statementInsertTest() throws SQLException {
        // 1.1 Statement 插入测试
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO subject VALUES (4, 'a', 10, 100, 45, 1, CURRENT_TIMESTAMP)";
        int i = statement.executeUpdate(sql);
        System.err.println(i);
        statementQueryTest();
    }

    @Test
    public void statementDelTest() throws SQLException {
        // 1.2 Statement 删除测试
        Statement statement = connection.createStatement();
        String sql = "delete from subject where id = 1";
        int i = statement.executeUpdate(sql);
        System.err.println(i);
        statementQueryTest();
    }

    @Test
    public void statementUpdateTest() throws SQLException {
        // 1.3 Statement 更新测试
        Statement statement = connection.createStatement();
        String sql = "update subject set name = 'AAA' where id = 1";
        int i = statement.executeUpdate(sql);
        System.err.println(i);
        statementQueryTest();
    }

    @Test
    public void statementQueryTest() throws SQLException {
        // 1.4 Statement 查询测试
        Statement statement = connection.createStatement();
        String sql = "Select * from subject";
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        StringBuilder sb;
        // 取结果
        while (resultSet.next()) {
            sb = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                sb.append(resultSet.getString(i));
                sb.append(" ");
            }
            System.err.println(sb.toString());
        }
    }

    @Test
    public void metaDataTest() throws SQLException {
        // 1.5 Statement 元数据
        // 可以从元数据对象中获取表名，总列娄，列名，列对应数据类型等
        Statement statement = connection.createStatement();
        String sql = "Select * from subject";
        ResultSet resultSet = statement.executeQuery(sql);
        // 元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnLabel = metaData.getColumnLabel(i);
            String columnClassName = metaData.getColumnClassName(i);
            String columnTypeName = metaData.getColumnTypeName(i);
            String format = StrUtil.format("{}: {}: {}", columnLabel, columnClassName, columnTypeName);
            System.err.println(format);
        }
    }

    @Test
    public void prepareStatementUpdateTest() throws SQLException {
        // 2.1 prepareStatement 查询测试
        // PrepareStatement 用法与Statement基本差不多
        // 不同点在于可以起到预编译作用，使用?做为占位符，防止SQL注入
        String sql = "update subject set name = 'AAA' where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 1);
        int i = ps.executeUpdate();
        System.err.println(i);
        statementQueryTest();
    }

    @Test
    public void prepareStatementBatchInsertTest() throws SQLException {
        // 2.1 prepareStatement 批量插入测试
        String sql = "INSERT INTO subject VALUES (?, ?, 10, 100, 45, 1, CURRENT_TIMESTAMP)";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 4; i <= 10; i++) {
            ps.setInt(1, i);
            ps.setString(2, "AAA" + i);
            ps.addBatch();
        }
        int[] ints = ps.executeBatch();
        System.err.println(JSONUtil.toJsonStr(ints));
        statementQueryTest();
    }


}
