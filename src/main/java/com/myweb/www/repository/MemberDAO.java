package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberDAO {

	MemberVO selectMemberWhereEmail(String username);

	List<AuthVO> selectAuth(String username);

	int updateLastLogin(String authEmail);

	int insert(MemberVO mvo);

	int insertAuthMember(MemberVO mvo);

	int update(MemberVO mvo);

}
