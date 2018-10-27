package com.bean;

import java.util.List;
public class Page {
	private List records;
	private int pageNum;//��ǰҳ��
	private int totalPageNum;//��ҳ��
	private int prePageNum;//��һҳ
	private int nextPageNum;//��һҳ
	
	private int pageSize=3;//ÿҳ��ʾ�ļ�¼����
	private int totalRecordsNum;//�ܼ�¼����
	
	private int startIndex;//ÿҳ��ʼ��¼������
	
	private String url;//��ѯ��ҳ������ĵ�ַ

	public Page(int pageNum,int totalRecordsNum){
		this.pageNum = pageNum;
		this.totalRecordsNum = totalRecordsNum;
		
		//������ҳ��
		totalPageNum = totalRecordsNum%pageSize==0?totalRecordsNum/pageSize:(totalRecordsNum/pageSize+1);
		//���㿪ʼ��¼������
		startIndex = (pageNum-1)*pageSize;
	}
	
	public List getRecords() {
		return records;
	}

	public void setRecords(List records) {
		this.records = records;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getPrePageNum() {
		prePageNum = pageNum-1;
		if(prePageNum<1){
			prePageNum = 1;
		}
		return prePageNum;
	}

	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}

	public int getNextPageNum() {
		nextPageNum = pageNum+1;
		if(nextPageNum>totalPageNum){
			nextPageNum = totalPageNum;
		}
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecordsNum() {
		return totalRecordsNum;
	}

	public void setTotalRecordsNum(int totalRecordsNum) {
		this.totalRecordsNum = totalRecordsNum;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
