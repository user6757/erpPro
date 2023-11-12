package com.jin.erp.community.qna.vo;

import java.util.Date;

public class QnABoardVO {
	
	private Integer qnano;		//글번호
	private String title;		//제목
	private String writer;		//작성자
	private String content;		//내용
	private Date qnaregdate;		//작성날짜
	private Integer qnacnt;		//조회수
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

}
