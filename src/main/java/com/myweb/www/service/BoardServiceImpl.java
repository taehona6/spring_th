package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	private final BoardDAO bdao;
	private final FileDAO fdao;

	@Override
	@Transactional
	public long register(BoardDTO bdto) {
		
		int isOk = bdao.insert(bdto.getBvo());
		long bno = bdao.selectMaxBno();
		if(bdto.getFlist() == null) {
			return isOk>0? bno : 0;			
		}
		
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}
		return isOk>0? bno : 0;
	}

	@Override
	public List<BoardVO> getList() {
		return bdao.selectBoardList();
	}

	@Override
	public List<BoardVO> getListPaging(PagingVO pgvo) {
		return bdao.selectBoardListPaging(pgvo);
	}

	@Override
	public int getBoardTotalCount(PagingVO pgvo) {
		return bdao.selectTotalCount(pgvo);
	}

	@Override
	public BoardDTO getDetail(long bno) {
		BoardVO bvo = bdao.selectDetail(bno);
		List<FileVO> fileList = fdao.selectFileList(bno);
		
		return new BoardDTO(bvo,fileList); 
	}

	@Override
	public int addReadCount(long bno) {
		return bdao.updateReadCount(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		return bdao.update(bvo);
	}

	@Override
	public int modify(BoardDTO bdto,String[] uuids) {
		BoardVO bvo = bdto.getBvo();
		//title,content 업데이트
		int isOk = bdao.update(bdto.getBvo());
		
		//file테이블에 신규 파일 업로드
		if(bdto.getFlist()!=null) {
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bvo.getBno());
				isOk *= fdao.insertFile(fvo);
			}
		}
		
		//삭제한 파일 테이블에서 삭제
		for(String uuid : uuids) {
			isOk *= fdao.deleteFile(uuid);
		}
		return isOk;
	}

	@Override
	@Transactional
	public int remove(long bno) {
		fdao.deleteFileBno(bno);
		return bdao.delete(bno);
	}

}
