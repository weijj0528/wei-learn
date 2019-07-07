package com.weiun;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.weiun.jdbc.JdbcUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

/**
 * JDBC测试
 * 0.1 驱动加载与连接 {@link #before()}
 * 1.1 Statement 插入 {@link #statementInsertTest()}
 * 1.2 Statement 删除 {@link #statementDelTest()} ()}
 * 1.3 Statement 更新 {@link #statementUpdateTest()}
 * 1.4 Statement 查询 {@link #statementBatchTest()}
 * 1.5 Statement 查询 {@link #statementQueryTest()}
 * 1.6 Statement 元数据 {@link #metaDataTest()}
 * 1.6 Statement SQL注入演示 {@link #sqlInjectionTest()}
 * 2.1 PrepareStatement 更新 {@link #prepareStatementUpdateTest()}
 * 2.2 PrepareStatement 批量插入 {@link #prepareStatementBatchInsertTest()}
 * 2.3 PrepareStatement 主建获取 {@link #prepareStatementBatchInsertTest()}
 * 3.1 事务演示 {@link #transactionTest()}
 * 3.2 读未提交演示 {@link #readUncommittedTest()} 注需要在 Mysql或其他 环境测试 hsql 暂不支持多事务处理
 * 3.3 读提交演示 {@link #readCommittedTest()} 注需要在 Mysql或其他 环境测试 hsql 暂不支持多事务处理
 * 3.4 可重复读演示 {@link #repeatableReadTest()} 注需要在 Mysql或其他 环境测试 hsql 暂不支持多事务处理
 * 3.5 序列化演示 {@link #serializableTest()} 注需要在 Mysql或其他 环境测试 hsql 暂不支持多事务处理
 * 4.1 数据库连接池演示
 *
 * @author Administrator
 * @createTime 2019/6/29 20:39
 * @description JDBC测试
 */
public class JdbcTest {

    /**
     * Jdbc程序中的Connection，它用于代表数据库的链接，
     * Collection是数据库编程中最重要的一个对象，
     * 客户端与数据库所有交互都是通过connection对象完成的，创建方法为：
     * {@link DriverManager#getConnection(String, String, String)}
     * 其他常用方法有：
     * {@link Connection#createStatement()} 创建向数据库发送sql的statement对象。
     * {@link Connection#prepareStatement(String)} 创建向数据库发送预编译sql的PrepareSatement对象。
     * {@link Connection#prepareCall(String)} 创建执行存储过程的callableStatement对象。
     * {@link Connection#setAutoCommit(boolean)} 设置事务是否自动提交。
     * {@link Connection#commit()} 在链接上提交事务。
     * {@link Connection#rollback()} 在此链接上回滚事务。
     */
    private Connection connection;

    private String url = "jdbc:hsqldb:mem:test";
    private String user = "SA";
    private String password = "";

    /**
     * JDBC驱动加载有两种方式
     * 1、Class.forName(driverClass);
     * 2、DriverManager.registerDriver();
     * <p>
     * 推荐使用第一种方式，查看 com.mysql.jdbc.Driver
     * 会发现其内部静态代码中使用 DriverManager.registerDriver();进行了驱动加载
     * 如使用第2种方式在加载类是同样也会再执行一次，这样就会了加载两个驱动了
     * <p>
     * 注：现代JDBC驱动可以不用显示加载
     *
     * @throws Exception
     */
    @Before
    public void before() throws Exception {
        // 构建H2内存数据库
        connection = JdbcUtils.getConnection(url, user, password);
        // 使用Mybatis ScriptRunner 执行SQl脚本初始化数据库
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        scriptRunner.setLogWriter(null);
        scriptRunner.runScript(Resources.getResourceAsReader("db_init.sql"));
        System.out.println("==============================");
        System.out.println("=====  DbCreateSuccess  ======");
        System.out.println("==============================");
    }

    /**
     * 连接使用后需要关闭
     *
     * @throws SQLException
     */
    @After
    public void after() throws SQLException {
        if (connection != null && !connection.isClosed())
            connection.close();
    }

    /**
     * 1.1 插入演示
     * Jdbc程序中的Statement对象用于向数据库发送SQL语句，创建方法为：
     * {@link Connection#createStatement()}
     * Statement对象常用方法：
     * {@link Statement#executeQuery(String)} 用于向数据发送查询语句
     * {@link Statement#executeUpdate(String)} 用于向数据库发送insert、update或delete语句
     * {@link Statement#execute(String)} 用于向数据库发送任意sql语句
     * {@link Statement#addBatch(String)} 把多条sql语句放到一个批处理中。
     * {@link Statement#executeBatch()} 向数据库发送一批sql语句执行。
     *
     * @throws SQLException
     */
    @Test
    public void statementInsertTest() throws SQLException {
        // 1.1 Statement 插入测试
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO subject VALUES (4, 'a', 10, 100, 45, 1, CURRENT_TIMESTAMP)";
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        statementQueryTest();
    }

    @Test
    public void statementDelTest() throws SQLException {
        // 1.2 Statement 删除测试
        Statement statement = connection.createStatement();
        String sql = "delete from subject where id = 1";
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        statementQueryTest();
    }

    @Test
    public void statementUpdateTest() throws SQLException {
        // 1.3 Statement 更新测试
        Statement statement = connection.createStatement();
        String sql = "update subject set name = 'AAA' where id = 1";
        int i = statement.executeUpdate(sql);
        System.out.println(i);
        statementQueryTest();
    }

    @Test
    public void statementBatchTest() throws SQLException {
        // 1.4 Statement 批量插入
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO subject VALUES ({}, '{}', 10, 100, 45, 1, CURRENT_TIMESTAMP)";
        for (int i = 0; i < 100; i++) {
            String format = StrUtil.format(sql, i + 4, "ABC" + i);
            System.out.println(format);
            statement.addBatch(format);
        }
        int[] ints = statement.executeBatch();
        System.out.println(ints);
        statementQueryTest();
    }

    /**
     * 查询，从结果集中取出查询的值
     * Jdbc程序中的ResultSet用于代表Sql语句的执行结果。
     * Resultset封装执行结果时，采用的类似于表格的方式，
     * ResultSet 对象维护了一个指向表格数据行的游标，初始的时候，游标在第一行之前，
     * 调用ResultSet.next() 方法，可以使游标指向具体的数据行，进行调用方法获取该行的数据。
     * 常用方法有：
     * {@link ResultSet#next()} 移动到下一行
     * {@link ResultSet#previous()} 移动到前一行
     * {@link ResultSet#absolute(int)} 移动到指定行
     * {@link ResultSet#beforeFirst()} 移动resultSet的最前面。
     * {@link ResultSet#afterLast()} 移动到resultSet的最后面。
     *
     * @throws SQLException
     */
    @Test
    public void statementQueryTest() throws SQLException {
        // 1.5 Statement 查询测试
        Statement statement = connection.createStatement();
        String sql = "Select * from subject";
        ResultSet resultSet = statement.executeQuery(sql);
        readResultSet(resultSet);
    }

    private void readResultSet(ResultSet resultSet) throws SQLException {
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
            System.out.println(sb.toString());
        }
    }

    @Test
    public void metaDataTest() throws SQLException {
        // 1.6 Statement 元数据
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
            System.out.println(format);
        }
    }

    /**
     * 1.7 Statement  sql注入
     * 场景，查询指定ID的subject
     *
     * @throws SQLException
     */
    @Test
    public void sqlInjectionTest() throws SQLException {
        Statement statement = connection.createStatement();
        String where = "1";
        String sql = "Select * from subject where id = ";
        ResultSet resultSet = statement.executeQuery(sql + where);
        readResultSet(resultSet);
        // sql注入
        System.out.println("sql注入后查询结果------------------------------");
        where = "1 or 1 = 1";
        resultSet = statement.executeQuery(sql + where);
        readResultSet(resultSet);
    }

    /**
     * PreperedStatement是Statement的了类，它的实例对象可以通过调用：
     * {@link Connection#prepareStatement(String)}
     * 它允许允许使用占位符的形式描述SQL中的参数，简化sql语句的编写
     * PreperedStatement与Statement用法基本一致
     * 不同之处在于
     * 1、PreperedStatement可以使得SQL预编译以提高效率
     * 2、PreperedStatement对于sql中的参数，允许使用占位符的形式进行替换，简化SQL编写，还起到防止SQl注入问题
     * 3、批处理时只能处理一类SQL语句，而Statement可以发送任意SQL语句
     * 4、SQL注入问题演示 {@link #sqlInjectionTest()}
     *
     * @throws SQLException
     */
    @Test
    public void prepareStatementUpdateTest() throws SQLException {
        // 2.1 prepareStatement 查询测试
        // PrepareStatement 用法与Statement基本差不多
        // 不同点在于可以起到预编译作用，使用?做为占位符，防止SQL注入
        String sql = "update subject set name = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "123");
        ps.setInt(2, 1);
        int i = ps.executeUpdate();
        System.out.println(i);
        statementQueryTest();
    }

    /**
     * 注意，不再添加整条的SQL语句，只设置对应参数即可
     *
     * @throws SQLException
     */
    @Test
    public void prepareStatementBatchInsertTest() throws SQLException {
        // 2.2 prepareStatement 批量插入测试
        String sql = "INSERT INTO subject VALUES (?, ?, 10, 100, 45, 1, CURRENT_TIMESTAMP)";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int i = 4; i <= 10; i++) {
            ps.setInt(1, i);
            ps.setString(2, "AAA" + i);
            ps.addBatch();
        }
        int[] ints = ps.executeBatch();
        System.out.println(JSONUtil.toJsonStr(ints));
        statementQueryTest();
    }

    @Test
    public void getGeneratedKeysTest() throws SQLException {
        // 2.3 主键获取
        String sql = "INSERT INTO subject(name,age,height,weight,active,dt) VALUES ('BBB', 10, 100, 45, 1, CURRENT_TIMESTAMP)";
        PreparedStatement ps = connection.prepareStatement(sql);
        int i = ps.executeUpdate();
        System.out.println(i);
        ResultSet generatedKeys = ps.getGeneratedKeys();
        ResultSetMetaData metaData = generatedKeys.getMetaData();
        int columnCount = metaData.getColumnCount();
        System.out.println(columnCount);
        while (generatedKeys.next()) {
            String string = generatedKeys.getString(1);
            System.out.println(string);
        }
        statementQueryTest();
    }

    /**
     * 事务演示
     * 修改两记录
     * 1、取消自动提交{@link Connection#setAutoCommit(boolean)}
     * 2、正常处理{@link Connection#commit()}
     * 3、出错{@link Connection#rollback()}
     *
     * @throws SQLException
     */
    @Test
    public void transactionTest() throws SQLException {
        try {
            connection.setAutoCommit(false);

            String sql = "update subject set name = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            // 第一条记录
            ps.setString(1, "AAA");
            ps.setInt(2, 1);
            int i = ps.executeUpdate();
            System.out.println("--------------------------");
            statementQueryTest();
            // 第二条记录
            ps.setString(1, "BBB");
            // 演示成功与失败情况
            ps.setInt(2, 4);
            i = ps.executeUpdate();
            if (i <= 0) {
                // 未修改数据抛出异常触发回滚
                throw new RuntimeException("error");
            }
            System.out.println("--------------------------");
            statementQueryTest();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
        System.out.println("--------------------------");
        statementQueryTest();
    }

    /**
     * 读未提交
     * 可以读取其他事务还未提交的数据，会导致 脏读 现象
     * 脏读：可以查询到其他事务还未提交的数据，如其他事务回滚，那读取到的数据应是无效数据
     *
     * @throws SQLException
     */
    @Test
    public void readUncommittedTest() throws SQLException {
        // 设置隔离级别为读未提交
        connection.setTransactionIsolation(TransactionIsolationLevel.READ_UNCOMMITTED.getLevel());
        transactionIsolation();
    }

    /**
     * 读提交
     * 可以读取其他事务提交后的数据，会导致 幻读 现象
     * 幻读：查询数据后其他事务修改了数据，再次查询时数据不一致
     *
     * @throws SQLException
     */
    @Test
    public void readCommittedTest() throws SQLException {
        // 设置隔离级别为读提交
        connection.setTransactionIsolation(TransactionIsolationLevel.READ_COMMITTED.getLevel());
        transactionIsolation();
    }

    /**
     * 可重复读
     * 不可以读取其他事务的数据，任何时间查询的数据均一致（事务中的修改不考虑）
     *
     * @throws SQLException
     */
    @Test
    public void repeatableReadTest() throws SQLException {
        // 设置隔离级别为可重复读
        connection.setTransactionIsolation(TransactionIsolationLevel.REPEATABLE_READ.getLevel());
        transactionIsolation();
    }

    /**
     * 序列化
     * 所有事务同步执行，最严格的带离级别，性能最差
     *
     * @throws SQLException
     */
    @Test
    public void serializableTest() throws SQLException {
        // 设置隔离级别为可重复读
        connection.setTransactionIsolation(TransactionIsolationLevel.SERIALIZABLE.getLevel());
        transactionIsolation();
    }

    private void transactionIsolation() throws SQLException {
        System.out.println("最初始数据---------------------------");
        statementQueryTest();
        // 事务2修改数据
        Connection connection2 = JdbcUtils.getConnection(url, user, password);
        connection2.setAutoCommit(false);
        String sql = "update subject set name = 'ABC' where id = 1";
        Statement statement = connection2.createStatement();
        int i = statement.executeUpdate(sql);
        if (i <= 0) {
            throw new RuntimeException("Error");
        }
        System.out.println("事务2修改后还未提交---------------------------");
        statementQueryTest();
        System.out.println("事务2修改后已提交---------------------------");
        // 提交
        connection2.commit();
        statementQueryTest();
    }

}
