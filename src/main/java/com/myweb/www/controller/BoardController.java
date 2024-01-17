package com.myweb.www.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/*")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

	private final BoardService bsv;
	
	@GetMapping("/register")
	public void registerForm() {
		log.info("aa");
	}
	
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		long bno = bsv.register(bvo);
		
		return "redirect:/board/"+bno;
	}
	
	@GetMapping("/{bno}")
	public String detail(@PathVariable long bno,Model m,HttpServletRequest request) {
		HttpSession ses = request.getSession();
		List<Long> readList = null;
		BoardVO bvo = bsv.getDetail(bno);
		if(bvo == null) {
			return "index";
		}
		
		readList = (ArrayList<Long>)ses.getAttribute("readList");
		if(readList == null) {
			readList = new ArrayList<Long>();
			bsv.addReadCount(bno);
			readList.add(bno);
			ses.setAttribute("readList", readList);
		}else {
			if(!readList.contains(bno)) {
				readList.add(bno);
				bsv.addReadCount(bno);
				ses.setAttribute("readList", readList);
			}
		}
		m.addAttribute("bvo",bvo);
		return "/board/detail";
	}
	
	@GetMapping
	public String list(Model m,PagingVO pgvo) {
		List<BoardVO> boardList = null;
//		boardList = bsv.getList();
		boardList = bsv.getListPaging(pgvo);
		m.addAttribute("list",boardList);
		int totalCount = bsv.getBoardTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("ph",ph);
		log.info("aa");
		return "/board/list";
	}	
	
}
