package com.myweb.www.service;

import com.myweb.www.security.MemberVO;

public interface MemberService {

	boolean updateLastLogin(String authEmail);

	int register(MemberVO mvo);

	boolean isDuplicated(String email);

}
