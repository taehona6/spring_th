package com.myweb.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {

	private final MemberService msv;
	
	@GetMapping("/register")
	public void registerForm() {
	}
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		log.info("register : mvo = {}",mvo);
		msv.register(mvo);
		return "redirect:/";
	}
	@ResponseBody
	@PostMapping("/validate")
	public String registerEmailValidate(@RequestBody String email) {
		log.info("validation : email = {}",email);
		
		//중복이면(해당 email이 존재하면 true) 0      중복이 아니면 1
		return msv.isDuplicated(email)? "0" : "1";
	}
}
