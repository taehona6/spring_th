package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.CommentVO;

public interface CommentDAO {

	int insertComment(CommentVO cvo);

	List<CommentVO> selectCommentList(long bno);

	int updateComment(CommentVO cvo);

}
