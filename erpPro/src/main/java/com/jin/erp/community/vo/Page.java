package com.jin.erp.community.vo;

public class Page {
	
	private int page; //���� ������ ��ȣ
	private int total;	// �� ������ ��
	private int startpage; // ������ ���� ��ȣ
	private int endpage;	//������ �� ��ȣ
	private int maxpage;	//��������������ȣ
	
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
