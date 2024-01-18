package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectBoardList();

	List<BoardVO> selectBoardListPaging(PagingVO pgvo);

	int selectTotalCount(PagingVO pgvo);

	long selectMaxBno();

	BoardVO selectDetail(long bno);

	int updateReadCount(long bno);

	int update(BoardVO bvo);

}
