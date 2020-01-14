package com.bzz.cloud.utils;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
	// 数据库配置
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://192.168.132.150:3306/bzz?useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true&useSSL=false&serverTimezone=GMT%2B8";
	private static final String username = "root";
	private static final String password = "root";

	// 获取连接
	public static Connection getConnection() {

		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 关闭连接
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static DataSource getDataSource(){
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName(driver);
		ds.setJdbcUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setPoolName("mysql pool");
		return ds;
	}
}
