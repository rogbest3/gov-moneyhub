package com.moneyhub.web.pxy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.moneyhub.web.aop.TxMapper;
import com.moneyhub.web.cus.Customer;
import com.moneyhub.web.cus.CustomerMapper;

@Component("manager")
public class CustomerProxy extends Proxy{
	@Autowired CustomerMapper cusMapper;
	@Autowired TxMapper txMapper;
	@Autowired Customer cus;
	
	public String makeName() {
		List<String> fname = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
			"송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
			"우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
	      	"변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
	      	"옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
		List<String> name = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
			"단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
			"백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
			"숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
			"유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
			"중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
			"혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
	    	"솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
	    	"열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
	    	"흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
	 	Collections.shuffle(fname);
	  	Collections.shuffle(name);	 
	  	return fname.get(0) + name.get(1) + name.get(2);
    }
	
	public String makeEmail() {		//'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		List<Character> chars = Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',											
											'0','1','2','3','4','5','6','7','8','9');
//		Collections.shuffle(chars);
		int first = 5, last = 10; // 5 ~ 10자리
		String id="";
/*		int size = 0;	
		int num = (int) (Math.random()*chars.size());
		for(int i = 0; i < 10; i++) {
			size = (int) (Math.random()*(last-first)) + first;		
			System.out.println("random id_size : " + size);
		}*/
		id = chars.get((int) (Math.random()*(chars.size()-10))) + "";
		for(int j = 0; j < (int) (Math.random()*(last-first)) + first; j++) {
			id += chars.get((int) (Math.random()*chars.size()));		
		}
		System.out.println("random id : " + id);
		
		List<String> mailType = Arrays.asList("@chol.com", "@daum.net", "@dreamwiz.com", "@empal.com", "@freechal.com", "@gmail.com", "@hanmail.net",
											"@hanmir.com", "@hitel.net", "@hotmail.com", "@intizen.com", "@kebi.com", "@korea.com", "@lycos.co.kr", 
											"@nate.com", "@naver.com", "@netian.com", "@orgio.net", "@teramail.com", "@wail.co.kr", "@yahoo.co.kr");
		Collections.shuffle(mailType);
		String email = id + mailType.get(0);
		
		System.out.println(email);
		return email;
	}

	public String makePwd() {
		List<Character> chars = Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
											'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
											'0','1','2','3','4','5','6','7','8','9');
		int first = 6, last = 16; // 6 ~ 15자리
		String pwd="";
		
		for(int j = 0; j < (int) (Math.random()*(last-first)) + first; j++) {
			pwd += chars.get((int) (Math.random()*chars.size()));		
		}
		System.out.println("random pwd : " + pwd);
		return pwd;
	}
	public String makedate() {
		int yearFirst = 2010, yearLast = 2019; // 2000 ~ 2019자리
		int monthFirst = 1, monthLast = 12; // 1 ~ 12자리
		int dayFirst = 1; 
		yearLast++;
		monthLast++;
		
		int[] dayLastArray = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int month = (int)(Math.random()*(monthLast - monthFirst))+monthFirst;
		
		int dayLast = 0;
		for(int i=0; i<dayLastArray.length; i++) {
			if( month == i+1 ) {
				dayLast = dayLastArray[i];
				dayLast++;
			}	
		}
		
		String date = String.format("%d-%02d-%02d", (int)(Math.random()*(yearLast - yearFirst))+yearFirst, 
													month,
													(int)(Math.random()*(dayLast - dayFirst))+dayFirst);
		System.out.println(date);
		
		return date;
	}
	@Transactional
	public boolean existEmail(String cemail) {
		boolean flag = false;
		
		if( cusMapper.existEmail(cemail) == 1 ) {	// 중복이면 true
			flag = true;
		}
		return flag;
	}
	
	@Transactional
	public void makeCustomer() {
		String cemail = makeEmail();
		
		if(!existEmail(cemail)) {
			cus.setCemail(cemail);
			cus.setCpwd(makePwd());
			cus.setCname(makeName());
			cus.setCdate(makedate());
			cusMapper.insertCustomer(cus);
			System.out.println("join 성공");
		}else {
			System.out.println("이메일 중복 - join 실패");
		}
	}
/*	public void makeCusRepet(int num) {
		for(int i = 0; i<num; i++)
			makeCustomer();
	}*/
	
	@Transactional
	public void insertCustomers() {
		for(int i = 0; i<500; i++)
			makeCustomer();
	}

	public void truncateCustomers() {
//		cusMapper.truncateCustomer(paramMap);
		
	}
}
