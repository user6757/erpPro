package com.jin.erp.mroom.vo;

import java.util.Date;

public class MroomVO {
	
	private int mrNo;
	private String mrUsers;
	private int mrTime;
	private String mrTitle;
	private String mrContent;
	private int mrMax;
	private Date mrRegDate;
	private String userId;
	private int fileNo;
	
	public int getMrNo() {
		return mrNo;
	}
	public void setMrNo(int mrNo) {
		this.mrNo = mrNo;
	}
	public String getMrUsers() {
		return mrUsers;
	}
	public void setMrUsers(String mrUsers) {
		this.mrUsers = mrUsers;
	}
	
	public int getMrTime() {
		return mrTime;
	}
	public void setMrTime(int mrTime) {
		this.mrTime = mrTime;
	}
	public String getMrTitle() {
		return mrTitle;
	}
	public void setMrTitle(String mrTitle) {
		this.mrTitle = mrTitle;
	}
	public String getMrContent() {
		return mrContent;
	}
	public void setMrContent(String mrContent) {
		this.mrContent = mrContent;
	}
	public int getMrMax() {
		return mrMax;
	}
	public void setMrMax(int mrMax) {
		this.mrMax = mrMax;
	}
	public Date getMrRegDate() {
		return mrRegDate;
	}
	public void setMrRegDate(Date mrRegDate) {
		this.mrRegDate = mrRegDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

}
