package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> selectFileList(long bno);

	int deleteFile(String uuid);

	int deleteFileBno(long bno);

}
