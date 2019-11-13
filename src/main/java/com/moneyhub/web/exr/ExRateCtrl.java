package com.moneyhub.web.exr;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneyhub.web.pxy.PageProxy;
import com.moneyhub.web.pxy.Trunk;
import com.moneyhub.web.enums.SQL;
import com.moneyhub.web.pxy.Box;
import com.moneyhub.web.pxy.ExrateProxy;
import com.moneyhub.web.utl.Printer;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/exrates/")
public class ExRateCtrl {
	
	
	@Autowired ExRate exr;
	@Autowired Printer p;
	@Autowired ExrateProxy exProxy;
	@Autowired Trunk<Object> trunk;
	@Autowired ExRateMapper exMapper;
	
	@GetMapping("/")
	public Map<?, ?> exRateRW(){
		p.accept("exRateRW 진입");

		exProxy.rwTXT();
		trunk.put(Arrays.asList("msg"), Arrays.asList("success"));
		return trunk.get();
	}
	
	@GetMapping("/create/table")
	public Map<?, ?> createExrate(){
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("CREATE_EXRATE", SQL.CREATE_EXRATE.toString());
		p.accept("테이블 쿼리 생성 : \n" + paramMap.get("CREATE_EXRATE"));
		Consumer<HashMap<String, String>> c = t -> exMapper.createExrate(t);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "SUCCESS");
		return paramMap;
	}
	
	@GetMapping("/delete/table")
	public Map<?, ?> deleteCustomer(){
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("DROP_EXRATE", SQL.DROP_EXRATE.toString());
		p.accept("테이블 쿼리 삭제 : \n" + paramMap.get("DROP_EXRATE"));
		Consumer<HashMap<String, String>> c = t -> exMapper.deleteExrate(t);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "SUCCESS");
		
		return paramMap;
	}
}
