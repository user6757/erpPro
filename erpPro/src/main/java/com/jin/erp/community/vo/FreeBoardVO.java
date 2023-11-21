package com.jin.erp.community.vo;

import java.util.Date;

public class FreeBoardVO {
	
	private Integer seq;		//글번호
	private String title;		//제목
	private String writer;		//작성자
	private String content;		//내용
	private Date regdate;		//작성날짜
	private String strRegdate;
	private Integer cnt;		//조회수
	private String filename;
	private String filedate;
	private String keyword;
	private String type;
	private int start;
	private int limit;
	
	public FreeBoardVO() {
		limit=10;
	}
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiledate() {
		return filedate;
	}

	public void setFiledate(String filedate) {
		this.filedate = filedate;
	}

	public String getStrRegdate() {
		return strRegdate;
	}

	public void setStrRegdate(String strRegdate) {
		this.strRegdate = strRegdate;
	}
	
}
