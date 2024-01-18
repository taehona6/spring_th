package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	long register(BoardDTO boardDTO);

	List<BoardVO> getList();

	List<BoardVO> getListPaging(PagingVO pgvo);

	int getBoardTotalCount(PagingVO pgvo);

	BoardDTO getDetail(long bno);

	int addReadCount(long bno);

	int modify(BoardVO bvo);

	int modify(BoardDTO boardDTO, String[] uuidArray);

	int remove(long bno);

}
