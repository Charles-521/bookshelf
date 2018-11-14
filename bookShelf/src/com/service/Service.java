package com.service;

import java.util.List;

import com.bean.BookBean;
import com.bean.Page;

public interface Service {
	Page findBookPageRecords(String num);
	//Page findBookPageRecordsByKeyWords(String keywords);
	Page findBookPageRecordsByName(String keywords);
	Page findBookPageRecordsByISBN(String keywords);
	Page findBookPageRecordsByCourseCode(String keywords);
	List<BookBean> findBookCartByUserID(int userID);
	boolean addLikeBook(int userID, int bookID);
	boolean addCartBook(int userID, int bookID);	
}
