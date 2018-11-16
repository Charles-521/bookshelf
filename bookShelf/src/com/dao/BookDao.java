package com.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.bean.BookBean;

public interface BookDao {
	public boolean addBook(BookBean bookBean);
	public int addLikeBook(int userID, int bookID);
	public boolean removeLikeBook(int userID, int bookID);
	public int addCartBook(int userID, int bookID);
	public List<BookBean> findLikeBookByUserID(int userID);
	public List<BookBean> findCartBookByUserID(int userID);
	public List<BookBean> findPageRecords(int startIndex, int pageSize);
	public List<BookBean> findLikePageRecords(int startIndex, int pageSize, int userID);
	public List<BookBean> findPageRecordsByName(int startIndex, int pageSize, String keyWords);
	public List<BookBean> findPageRecordsByISBN(int startIndex, int pageSize, String keyWords);
	public List<BookBean> findPageRecordsByCourseCode(int startIndex, int pageSize, String keyWords);
	public BookBean findBookById(int id);
	public int getTotalRecordsNum();	
	public int getTotalLikeRecordsNum(int userID);
	public int getTotalRecordsNumByName(String keyWords);
	public int getTotalRecordsNumByISBN(String keyWords);
	public int getTotalRecordsNumByCourseCode(String keyWords);
	public int checkout(int userID, BigDecimal total, String bookIDs) throws SQLException;
	public List<BookBean> findPageRecordsByOwnerID(int startIndex, int pageSize, int ownerID);
	public int getTotalRecordsNumByOwnerID(int ownerID);
	public boolean delBook(int userID, int bookID);
	public boolean updateBook(int bookID, String name, BigDecimal price, String ISBN, String courseCode, String desc);
	
}
