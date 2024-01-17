package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	private final BoardDAO bdao;

	@Override
	@Transactional
	public long register(BoardVO bvo) {
		
		int isOk = bdao.insert(bvo);

		return isOk>0? bdao.selectMaxBno() : 0;
	}

	@Override
	public List<BoardVO> getList() {
		return bdao.selectBoardList();
	}

	@Override
	public List<BoardVO> getListPaging(PagingVO pgvo) {
		return bdao.selectBoardListPaging(pgvo);
	}

	@Override
	public int getBoardTotalCount(PagingVO pgvo) {
		return bdao.selectTotalCount(pgvo);
	}

	@Override
	public BoardVO getDetail(long bno) {
		return bdao.selectDetail(bno);
	}

}
