package com.ccnu.edu.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by 董乐强 on 2017/11/29.
 * JDBC工具lei
 * 1)获取Connection
 * 2)释放资源
 */
public class JDBCUtil {

    /**
     * 获取Connection
     * @return所获得到的jdbc的Connection
     */
    public static Connection getConnection() throws Exception {
        /**
         * 不建议把硬编码配置到代码上
         * 配置性的建议写到配置文件中,因为生产环境中不能改代码
         */
//        String url="jdbc:mysql://localhost:3306/jpa";
//        String user="root";
//        String password="root";
//        String driverClass = "com.mysql.jdbc.Driver";
        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        String url=properties.getProperty("jdbc.url");
        String user=properties.getProperty("jdbc.user");
        String password=properties.getProperty("jdbc.password");
        String driverClass = properties.getProperty("jdbc.driverClass");
        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    public static void release(ResultSet resultSet, Statement statement,Connection connection){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


















}
