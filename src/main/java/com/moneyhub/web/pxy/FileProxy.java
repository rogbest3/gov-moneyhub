package com.moneyhub.web.pxy;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.moneyhub.web.enums.Path;
import com.moneyhub.web.utl.Printer;

@Component("fileProxy")
public class FileProxy extends Proxy{
	@Autowired Printer p;
	
	public void fileUpload(MultipartFile[] uploadFile) {
		p.accept("파일 업로드");
		String uploadFolder = Path.UPLOAD_PATH.toString();
		
		// 날짜명 폴더 자동 생성
	//	File uploadPath = new File(uploadFolder, getFolder());
		File uploadPath = makeDir(uploadFolder, getFolder());
		if(uploadPath.exists() == false) {	// 같은 날짜 폴더 확인
			uploadPath.mkdirs();	// 여러	
		}
					
		for(MultipartFile f : uploadFile) {
			String fname = f.getOriginalFilename();	// .부터 출력(.전은 삭제), + 1하면 .까지 잘라버림
			String extension = fname.substring(fname.lastIndexOf(".") + 1);	// . 뒤를 확장자
			p.accept("확장자 : " + extension);
			fname = fname.substring(fname.lastIndexOf("\\") + 1, fname.lastIndexOf(".")); // . 앞에를 이름
			p.accept("파일명 : " + fname);
			
		//	File savedFile = new File(uploadPath, fname + "-" +UUID.randomUUID().toString() + "." + extension);
			File savedFile = makeFile(uploadPath, fname + "-" +UUID.randomUUID().toString() + "." + extension);
			try {
				f.transferTo(savedFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
/*		File uploadPath = new GenFile<String>().makeFile(uploadFolder, getFolder());
		System.out.println("파일경로1: " + uploadPath.getPath());
		File savedFile = new GenFile<File>().makeFile(uploadPath, "");
		System.out.println("파일경로1: " + savedFile.getPath());*/
		
	}
	
	public String getFolder() {
		return currentDate().replace("-", File.separator);
	}
}
