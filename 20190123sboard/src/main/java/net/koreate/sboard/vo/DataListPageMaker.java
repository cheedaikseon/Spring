package net.koreate.sboard.vo;

import java.util.List;

import net.koreate.sboard.util.PageMaker;

public class DataListPageMaker {
	
	List<CommentVO> list;
	
	PageMaker pageMaker;	
	

	public List<CommentVO> getList() {
		return list;
	}

	public void setList(List<CommentVO> list) {
		this.list = list;
	}

	public PageMaker getPageMaker() {
		return pageMaker;
	}

	public void setPageMaker(PageMaker pageMaker) {
		this.pageMaker = pageMaker;
	}
	
	

}
