package com.moneyhub.web.acc;

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
@RequestMapping("/account")
public class AccountCtrl {
	@Autowired Printer p;
	@Autowired AccountMapper accMapper;
	
	@GetMapping("/create/table")
	public Map<?, ?> createCustomer(){
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("CREATE_ACCOUNT", SQL.CREATE_ACCOUNT.toString());
		p.accept("테이블 쿼리 생성 : \n" + paramMap.get("CREATE_ACCOUNT"));
		IConsumer<HashMap<String, String>> c = t -> accMapper.createAccount(t);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "SUCCESS");
		
		return paramMap;
	}
}
