package com.myweb.www.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.FileHandler;
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
	private final FileHandler fh;
	@GetMapping("/register")
	public void registerForm() {
		log.info("aa");
	}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
//		long bno = bsv.register(bvo);
		List<FileVO> flist = null;
		if(files[0].getSize()>0) {
			flist = fh.uploadFiles(files);
		}
		long bno = bsv.register(new BoardDTO(bvo,flist));
		return "redirect:/board/"+bno;
	}
	
	@GetMapping("/{bno}")
	public String detail(@PathVariable long bno,Model m,HttpServletRequest request) {
		readCountLogic(bno, request);
		BoardDTO bdto = bsv.getDetail(bno);
		m.addAttribute("bdto",bdto);
		return "/board/detail";
	}
	
	public void readCountLogic(long bno, HttpServletRequest request) {
		HttpSession ses = request.getSession();
		List<Long> readList = null;
		
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
	}
	
	
	@GetMapping("/list")
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
	
	@GetMapping("/{bno}/modify")
	public String modifyForm(@PathVariable long bno,Model m) {
		BoardDTO bdto = bsv.getDetail(bno);
		m.addAttribute("bdto",bdto);
		return "/board/modify";
	}
	@PostMapping("/modify")
	public String modify(BoardVO bvo, @RequestParam(name="files", required = false)MultipartFile[] files,String uuids) {
		log.info("bvo :: {}",bvo);		
//		bsv.modify(bvo);
		
		List<FileVO> flist = null;
		if(files[0].getSize()>0) {
			flist = fh.uploadFiles(files);
		}
		String[] uuidArray = uuids.split(",");
		log.info("uuids :: >> {}",uuids);
		log.info("uuid array :: >> {}",uuidArray.toString());
		bsv.modify(new BoardDTO(bvo,flist),uuidArray);
		
		
		
		long bno = bvo.getBno();
		return "redirect:/board/"+bno;
	}
	@GetMapping("/{bno}/delete")
	public String delete(@PathVariable long bno) {
		bsv.remove(bno);
		
		return "redirect:/board/list";
	}
}
