package com.myweb.www.service;

import org.springframework.stereotype.Service;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberDAO mdao;
	
	@Override
	public boolean updateLastLogin(String authEmail) {
		// TODO Auto-generated method stub
		return mdao.updateLastLogin(authEmail) > 0 ? true :false;
	}

	@Override
	public int register(MemberVO mvo) {
		
		mdao.insert(mvo);
		return mdao.insertAuthMember(mvo); 
	}

	@Override
	public boolean isDuplicated(String email) {
		MemberVO mvo = mdao.selectMemberWhereEmail(email);
		return mvo == null? false : true; 
	}

}
