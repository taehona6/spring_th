package com.myweb.www.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

	private final CommentDAO cdao;
	private final BoardDAO bdao;

	@Override
	@Transactional
	public int addComment(CommentVO cvo) {
		cdao.insertComment(cvo);
		bdao.updateCmtQty(cvo.getBno());
		int cmtQty = bdao.selectCmtQty(cvo.getBno());
		return cmtQty;
		
	}
}
