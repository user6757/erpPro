package com.jin.erp.community.vo;

public class Page {
	
	private int page; //현제 페이지 번호
	private int total;	// 총 페이지 수
	private int startpage; // 페이지 시작 번호
	private int endpage;	//페이지 끝 번호
	private int maxpage;	//페이지마지막번호
	
	public Page() {
		page=1;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStartpage() {
		return startpage;
	}
	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}
	public int getEndpage() {
		return endpage;
	}
	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}
	public int getMaxpage() {
		return maxpage;
	}
	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}

}
