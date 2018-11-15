package com.bean;

import java.math.BigDecimal;
import java.util.List;

public class OrderInfo {
	private int id;
	private int buyerID;
	private BigDecimal payment;
	private String createDate;
	private String orderNumber;
	private int BookID;
	private int ownerId;
	private float price;
	private String name;
	private String courseCode;
	private String ISBN;
	private String picturePath;
	private String filename;
	private String description;
	private int state;
	private String phoneNumber;
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getOrderNo() {
		return orderNumber;
	}
	public void setOrderNo(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getBuyerID() {
		return buyerID;
	}
	public void setBuyerID(int buyerID) {
		this.buyerID = buyerID;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getBookID() {
		return BookID;
	}
	public void setBookID(int id) {
		this.BookID = id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String pn) {
		this.phoneNumber = pn;
	}
	
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
