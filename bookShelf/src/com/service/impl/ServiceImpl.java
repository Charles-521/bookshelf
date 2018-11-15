package com.service.impl;

import java.util.List;

import com.bean.BookBean;
import com.bean.Page;
import com.dao.BookDao;
import com.dao.impl.BookDaoImpl;
import com.service.Service;

public class ServiceImpl implements Service {
	
	private BookDao bookDao = new BookDaoImpl();
	@Override
	public Page findBookPageRecords(String num) {
		int pageNum = 1;
		if(num!=null&&!num.equals("")){
			pageNum = Integer.parseInt(num);
		}
		int totalRecordsNum = bookDao.getTotalRecordsNum();
		Page page = new Page(pageNum, totalRecordsNum);
		List<BookBean> records = bookDao.findPageRecords(page.getStartIndex(),page.getPageSize());
		page.setRecords(records);
		return page;
	}
	@Override	
	public Page findFavoriteBooks(String num, int userID) {
		int pageNum = 1;
		if(num!=null&&!num.equals("")){
			pageNum = Integer.parseInt(num);
		}
		int totalRecordsNum = bookDao.getTotalLikeRecordsNum(userID);
		Page page = new Page(pageNum, totalRecordsNum);
		List<BookBean> records = bookDao.findLikePageRecords(page.getStartIndex(),page.getPageSize(), userID);
		page.setRecords(records);
		return page;
	}
/*	@Override
	public Page findBookPageRecordsByKeyWords(String keyWords) {
		int pageNum = 1;
		int totalRecordsNum = bookDao.getTotalRecordsNumByKeyWords(keyWords);
		Page page = new Page(pageNum, totalRecordsNum);
		//List<BookBean> records = bookDao.findPageRecords(page.getStartIndex(),page.getPageSize());
		List<BookBean> records = bookDao.findPageRecordsByKeyWords(page.getStartIndex(), page.getPageSize(), keyWords);
		page.setRecords(records);
		return page;
	}*/
	@Override
	public Page findBookPageRecordsByName(String keywords) {
		int pageNum = 1;
		int totalRecordsNum = bookDao.getTotalRecordsNumByName(keywords);
		Page page = new Page(pageNum, totalRecordsNum);
		List<BookBean> records = bookDao.findPageRecordsByName(page.getStartIndex(), page.getPageSize(), keywords);
		page.setRecords(records);
		return page;
	}
	
	@Override
	public Page findBookPageRecordsByISBN(String keywords) {
		int pageNum = 1;
		int totalRecordsNum = bookDao.getTotalRecordsNumByISBN(keywords);
		Page page = new Page(pageNum, totalRecordsNum);
		List<BookBean> records = bookDao.findPageRecordsByISBN(page.getStartIndex(), page.getPageSize(), keywords);
		page.setRecords(records);
		return page;
	}
	
	@Override
	public Page findBookPageRecordsByCourseCode(String keywords) {
		int pageNum = 1;
		int totalRecordsNum = bookDao.getTotalRecordsNumByCourseCode(keywords);
		Page page = new Page(pageNum, totalRecordsNum);
		List<BookBean> records = bookDao.findPageRecordsByCourseCode(page.getStartIndex(), page.getPageSize(), keywords);
		page.setRecords(records);
		return page;
	}
	@Override
	public List<BookBean> findBookCartByUserID(int userID) {
		List<BookBean> records = bookDao.findCartBookByUserID(userID);
		
		return records;
	}
	
	@Override
	public int addLikeBook(int userID, int bookID) {
		return bookDao.addLikeBook(userID, bookID);		
	}
	
	@Override
	public boolean removeLikeBook(int userID, int bookID) {
		return bookDao.removeLikeBook(userID, bookID);
		
	}
	
	@Override
	public int addCartBook(int userID, int bookID) {
		return bookDao.addCartBook(userID, bookID);
	}

}
