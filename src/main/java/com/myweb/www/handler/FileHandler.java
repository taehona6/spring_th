package com.myweb.www.handler;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component
public class FileHandler {
	private final String UP_DIR = "D:\\_myProject\\_java\\_fileUpload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<FileVO>();
		String today = LocalDate.now().toString(); // 2024-01-10
		today = today.replace("-", File.separator);
		
		File folders = new File(UP_DIR,today);
		
		//폴더생성 exists
		if(!folders.exists()) {
			folders.mkdirs();  // mkdir() : 하나만 생성
		}
		
		//
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String fileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			log.info("MMMMMMM>>>originalFileName.indexof : {}",originalFileName.lastIndexOf(File.separator));
			log.info("MMMMMMM>>>originalFileName : {}",originalFileName);
			log.info(">>>file name : {}",fileName);
			fvo.setFileName(fileName);
			
			String uuid = UUID.randomUUID().toString();			
			fvo.setUuid(uuid);
			log.info(">>>uuid : {}",uuid);
			
			String fullFileName = uuid+"_"+fileName;
			log.info("MMMMMMM>>>fullFileName : {}",fullFileName);
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile); //저장
				//썸네일 생성 => 이미지 파일만 썸네일 생성 가능
				//이미지 인지 확인
				if(isImageFile(storeFile)) {
					fvo.setFileType(1); //이미지면 1
					
					File thumbNail = new File(folders, uuid+"_th_"+fileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("파일 생성 오류");
			}
			flist.add(fvo);
		}
		return flist;
	}
	
	//이미지 인지 확인하는 메서드
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile); // type을 확인해줌 image/jpg image/png
		return mimeType.startsWith("image")? true : false;
	}
}

