package com.util;

import java.sql.*;

public class DBconnTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
    	
    	final String url = "jdbc:mysql://localhost:3306/bookshelf?useunicuee=true& characterEncoding=utf8"; 
    	final String username = "root"; 
    	final String password = "root"; 
        //1.������������
        Class.forName("com.mysql.jdbc.Driver");
        //2.������ݿ�����
        Connection conn=DriverManager.getConnection(url, username, password);
        //3.ͨ�����ݿ�����Ӳ������ݿ⣬ʵ����ɾ�Ĳ飨ʹ��Statement�ࣩ
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from userinfo");
        //4.�������ݿ�ķ��ؽ��(ʹ��ResultSet��)
        while(rs.next()){
            System.out.println(rs.getString("name"));
        }
        
        //�ر���Դ
        rs.close();
        st.close();
        conn.close();
    }
}
