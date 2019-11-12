package com.moneyhub.web.ahs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneyhub.web.cmm.IConsumer;
import com.moneyhub.web.enums.SQL;
import com.moneyhub.web.utl.Printer;
@RestController
@RequestMapping("/accountHistory")
public class AccHistoryCtrl {
	@Autowired Printer p;
	@Autowired AccHistoryMapper acchisMapper;
	
	@GetMapping("/create/table")
	public Map<?, ?> createCustomer(){
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("CREATE_ACCHISTORY", SQL.CREATE_ACCHISTORY.toString());
		p.accept("테이블 쿼리 생성 : \n" + paramMap.get("CREATE_ACCHISTORY"));
		IConsumer<HashMap<String, String>> c = t -> acchisMapper.createAccHistory(t);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "SUCCESS");
		
		return paramMap;
	}
}
