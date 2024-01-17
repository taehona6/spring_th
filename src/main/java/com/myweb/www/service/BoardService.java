package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	long register(BoardVO bvo);

	List<BoardVO> getList();

	List<BoardVO> getListPaging(PagingVO pgvo);

	int getBoardTotalCount(PagingVO pgvo);

	BoardVO getDetail(long bno);

}
