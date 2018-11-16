package com.dao;

import com.bean.UserBean;

public interface UserDao {
	public boolean login(String name,String pwd);
	public boolean findUserByName(String name);
	public boolean register(UserBean user);
	public boolean update(int id,String name, String pwd,String phoneNum);
	public int findIdByName(String name);
}
