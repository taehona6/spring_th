package com.myweb.www.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Override
	public MemberVO getDetail(String email) {
		// TODO Auto-generated method stub
		return mdao.selectMemberWhereEmail(email);
	}

	@Override
	public int modify(MemberVO mvo) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		
		if(mvo.getPwd().length()==0 || mvo.getPwd()==null) {
			log.info("pw : "+mvo.getPwd());
			mvo.setPwd(mdao.selectMemberWhereEmail(mvo.getEmail()).getPwd());
		}else {
		String pwd = mvo.getPwd();
		String encPwd = pe.encode(pwd);
		mvo.setPwd(encPwd);
		}
		return mdao.update(mvo);
	}

}
