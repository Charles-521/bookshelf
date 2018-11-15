package com.service;

import java.util.List;

import com.bean.BookBean;
import com.bean.Page;

public interface Service {
	Page findBookPageRecords(String num);
	public Page findFavoriteBooks(String num, int userID);
	//Page findBookPageRecordsByKeyWords(String keywords);
	Page findBookPageRecordsByName(String keywords);
	Page findBookPageRecordsByISBN(String keywords);
	Page findBookPageRecordsByCourseCode(String keywords);
	List<BookBean> findBookCartByUserID(int userID);
	int addLikeBook(int userID, int bookID);
	boolean removeLikeBook(int userID, int bookID);
	int addCartBook(int userID, int bookID);
}
