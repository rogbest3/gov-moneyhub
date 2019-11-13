package com.moneyhub.web.enums;

public enum Path {
	UPLOAD_PATH, CRAWLING_TARGET,
	EXRATE_FILE_PATH;
	
	@Override
	public String toString() {
		String result = "";
		
		switch (this) {
		case UPLOAD_PATH:	// 실경로 
			result = "C:\\Users\\admin\\GOVF1\\eGovFrameDev-3.8.0-64bit\\workspace\\moneyhub\\src\\main\\webapp\\resources\\upload\\temp";
			break;
		
		case CRAWLING_TARGET:	// 실경로 
			result = "http://m.ppomppu.co.kr/new/bbs_list.php?id=freeboard&page=";
			break;
			
		case EXRATE_FILE_PATH:	// 실경로 
			result = "C:\\Users\\admin\\GOVF1\\eGovFrameDev-3.8.0-64bit\\workspace\\moneyhub\\src\\main\\resources\\txt\\";
			break;
		
		}
		return result;
	}
}
