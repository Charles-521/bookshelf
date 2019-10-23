package com.util;

import java.sql.*;



public class DBconn {
//	static String url = "jdbc:mysql://ec2-52-77-0-56.ap-southeast-1.compute.amazonaws.com:3306/bookshelf?useunicode=true&characterEncoding=utf8"; 
//	static String username = "bookshelf"; 
//	static String password = "bookshelf123!"; 
	
	static String url = "jdbc:mysql://localhost:3306/bookshelf?useunicode=true&characterEncoding=utf8"; 
	static String username = "root"; 
	static String password = "root"; 
	static Connection  conn = null;
	static ResultSet rs = null;
	static PreparedStatement ps =null;
/*	public static void init(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			System.out.println("init [SQL���������ʼ��ʧ�ܣ�]");
			e.printStackTrace();
		}
	}*/
	
	public static Connection getConnection()  {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username,password);
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("connect failed");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	} 
	
	public static void close(ResultSet rs,Statement stat,Connection conn) {
		try {
			if(rs!=null)rs.close();
			if(stat!=null)stat.close();
			if(conn!=null)conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
