package com.util;

import java.sql.*;

public class DBconnTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
    	
    	final String url = "jdbc:mysql://localhost:3306/bookshelf?useunicuee=true& characterEncoding=utf8"; 
    	final String username = "root"; 
    	final String password = "root"; 

        Class.forName("com.mysql.jdbc.Driver");

        Connection conn=DriverManager.getConnection(url, username, password);
       
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from userinfo");

        while(rs.next()){
            System.out.println(rs.getString("name"));
        }
        
        rs.close();
        st.close();
        conn.close();
    }
}
