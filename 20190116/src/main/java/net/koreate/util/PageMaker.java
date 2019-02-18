package net.koreate.util;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	
	private int totalCount;			// 전체 게시물의 개수
	private int startPage;			// 게시판의 화면에 보여질 시작 페이지 번호
	private int endPage;			// 게시판의 화면에 보여질 마지막 페이지 번호
	private boolean prev;			// 이전 페이지 버튼 활성화 여부
	private boolean next;			// 다음 페이지 버튼 활성화 여부
	private int displayPageNum=10;	// 한번에 보여줄 페이지 개수
	
	Criteria cri;					// 게시물 검색 정보
	
	public void calcPaging() {
		
		endPage = (int)Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum;
		
		startPage = (endPage - displayPageNum)+1;
		
		int maxPage = (int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		
		if(endPage > maxPage)endPage = maxPage;
		
		prev = (startPage == 1) ? false : true;
		
		next = (endPage*cri.getPerPageNum() >= totalCount) ? false : true;
		
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcPaging();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public String makeSearchQuery(int page) {
		UriComponents uc = 
				UriComponentsBuilder.newInstance()
				.queryParam("page",page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
				.queryParam("keyword", ((SearchCriteria)cri).getKeyword())
				.build();
		return uc.toString();
	}
	
	
	
	
	
	
	
}
