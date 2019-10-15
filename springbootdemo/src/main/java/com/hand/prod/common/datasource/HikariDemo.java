package com.hand.prod.common.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jdk.internal.util.xml.impl.Input;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-10-
 */
@Slf4j
public class HikariDemo {

    private static String jdbcUrl = "";
    private static String user = "";
    private static String password = "";
    private static String dataSourceClassName = "";

    static {
        InputStream inputStream = HikariDemo.class.getClassLoader().getResourceAsStream("hikari.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            jdbcUrl = properties.getProperty("dataSource.jdbcUrl");
            user = properties.getProperty("dataSource.user");
            password = properties.getProperty("dataSource.password");
            dataSourceClassName = properties.getProperty("dataSourceClassName");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 描述：use the HikariConfig
     * @param
     * @param
     * @return
     */
    public static void useHikariConfig() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(user);
        config.setPassword(password);
        config.setDataSourceClassName(dataSourceClassName);
        HikariDataSource hikariDataSource = new HikariDataSource(config);
        Connection connection = hikariDataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from my_test");
        while(resultSet.next()) {
            log.info(resultSet.getString("name"));
        }
    }

    public static void userProperties() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("dataSourceClassName", "com.mysql.cj.jdbc.MysqlDataSource");
        properties.setProperty("dataSource.jdbcUrl", "jdbc:mysql://localhost:3306/test?serverTimezone=GMT");
        properties.setProperty("dataSource.user", "root");
        properties.setProperty("dataSource.password", "123456");
        properties.setProperty("dataSource.databaseName", "test");

        HikariConfig hikariConfig = new HikariConfig(properties);
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        Connection connection = hikariDataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from my_test");
        while(resultSet.next()) {
            log.info(resultSet.getString("name"));
        }
    }

    public static void main(String[] args) throws SQLException {
        useHikariConfig();
    }
}
