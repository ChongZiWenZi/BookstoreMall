package com.atguigu.bookstore.web;

import java.util.List;

public class Page<T> {
	// ��ǰ�ڼ�ҳ
	private int pageNo;
	// ��ǰҳ��List
	private List<T> list;
	// ÿҳ��ʾ��������¼
	private int pagesize;
	//���ж�������¼
	private int totalItemNumber;
	//��������Ҫ��pageNo��ʼ��
	public Page(int pageNo) {
		
		super();
		this.pageNo = pageNo;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getTotalItemNumber() {
		return totalItemNumber;
	}
	public void setTotalItemNumber(int totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}
	public int getPrevPage(){
		if(isHasPrev()){
			return getPageNo() - 1;
		}
		
		return getPageNo();
	}
	
	public int getNextPage(){
		if(isHasNext()){
			return getPageNo() + 1;
		}
		
		return getPageNo();
	}
	public boolean isHasNext(){
		if(getPageNo() < getTotalItemNumber()){
			return true;
		}
		
		return false;
	}
	
	public boolean isHasPrev(){
		if(getPageNo() > 1){
			return true;
		}
		
		return false;
	}
}
