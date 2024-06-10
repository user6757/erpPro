package com.jin.erp.community.qna.vo;

import java.util.Date;

public class QnABoardVO {
	
	private Integer qnano;		//�۹�ȣ
	private String title;		//����
	private String writer;		//�ۼ���
	private String content;		//����
	private Date qnaregdate;		//�ۼ���¥
	private Integer qnacnt;		//��ȸ��
	private String strdate;
	private String keyword;
	private String type;
	private int start;
	private int limit;
	
	public QnABoardVO() {
		limit=10;
	}
	
	public Integer getQnano() {
		return qnano;
	}
	public void setQnano(Integer qnano) {
		this.qnano = qnano;
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
	public Date getQnaregdate() {
		return qnaregdate;
	}
	public void setQnaregdate(Date qnaregdate) {
		this.qnaregdate = qnaregdate;
	}
	public Integer getQnacnt() {
		return qnacnt;
	}
	public void setQnacnt(Integer qnacnt) {
		this.qnacnt = qnacnt;
	}
	public String getStrdate() {
		return strdate;
	}
	public void setStrdate(String strdate) {
		this.strdate = strdate;
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

}
