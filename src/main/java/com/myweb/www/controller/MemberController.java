package com.myweb.www.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private final BCryptPasswordEncoder bcEncoder;
	@GetMapping("/register")
	public void registerForm() {
	}
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
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
	
	@GetMapping("/login")
	public void loginForm() {
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes re) {
		re.addAttribute("email",request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		return "redirect:/member/login";
	}
	
	@GetMapping({"/detail","/modify"})
	public void detail(Principal p, Model m) {
		MemberVO mvo = msv.getDetail(p.getName());
		log.info("mvo ::::: {} ",mvo);
		m.addAttribute("mvo",mvo);
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo,HttpServletRequest request,HttpServletResponse response) {
		log.info("post modify mvo::: {}",mvo);
		msv.modify(mvo);

		//로그아웃
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request,response, authentication);
		
		
		return "/member/login";
	}
}
