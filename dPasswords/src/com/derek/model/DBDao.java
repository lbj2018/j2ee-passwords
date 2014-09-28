package com.derek.model;

import java.sql.*;

public class DBDao {
	private static DBDao sDbDao;
	private Connection conn;
	private String driver;
	private String url;
	private String username;
	private String pass;
	
	public static DBDao get() {
		if (sDbDao == null) {			
			sDbDao = new DBDao("com.mysql.jdbc.Driver",
					"jdbc:mysql://localhost:3306", "root", "root");
		}
		return sDbDao;
	}

	private DBDao(String driver, String url, String username, String pass) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.pass = pass;
	}

	public Connection getConnection() throws Exception {
		if (conn == null) {
			Class.forName(this.driver);
			conn = DriverManager.getConnection(url, username, this.pass);
		}
		return conn;
	}
	
	public void create(String sql, Object... args) throws Exception {
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pstmt.setObject(i + 1, args[i]);
		}
		
		pstmt.executeUpdate();
		pstmt.close();
	}

	public boolean insert(String sql, Object... args) throws Exception {
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pstmt.setObject(i + 1, args[i]);
		}
		if (pstmt.executeUpdate() != 1) {
			return false;
		}
		pstmt.close();
		return true;
	}

	public ResultSet query(String sql, Object... args) throws Exception {
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pstmt.setObject(i + 1, args[i]);
		}
		ResultSet result = pstmt.executeQuery();
		return result;
	}

	public void modify(String sql, Object... args) throws Exception {
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pstmt.setObject(i + 1, args[i]);
		}
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public void update(String sql, Object... args) throws Exception {
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pstmt.setObject(i + 1, args[i]);
		}
		pstmt.executeUpdate();
		pstmt.close();
	}

	public void closeConn() throws Exception {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDriver() {
		return (this.driver);
	}

	public String getUrl() {
		return (this.url);
	}

	public String getUsername() {
		return (this.username);
	}

	public String getPass() {
		return (this.pass);
	}
}
