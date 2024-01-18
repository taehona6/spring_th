package com.myweb.www.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/comment/*")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService csv;
	
	@PostMapping
	public String postComment(@RequestBody CommentVO cvo) {
		log.info("cvo ::>> {}",cvo);
		
		int cmtQty = csv.addComment(cvo);
		log.info("cmtQTy ::>> {}",cmtQty);
		
		return String.valueOf(cmtQty);
	}
	
	@GetMapping(value = "/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CommentVO> getCommentList(@PathVariable long bno) {
		log.info("bno :: >> {}",bno);
		
		List<CommentVO> commentList = csv.getCommentList(bno);
		
		return commentList;
	}
}
