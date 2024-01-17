package com.myweb.www.handler;
import com.myweb.www.domain.PagingVO;

import lombok.Getter;

@Getter
public class PagingHandler {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int totalCount;
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		this.endPage = (int) Math.ceil(pgvo.getPageNo() / 10.0) * 10;
		this.startPage = endPage -9;
		
		int realEnd = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		
		if(realEnd < endPage) {
			this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
		
	}
	
}
