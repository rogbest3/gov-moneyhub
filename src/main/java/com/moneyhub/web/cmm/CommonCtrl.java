package com.moneyhub.web.cmm;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moneyhub.web.cus.CustomerMapper;
import com.moneyhub.web.enums.SQL;

@Controller
public class CommonCtrl { // POJO 방식 - extend 없음
	
	@Autowired CustomerMapper cusMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(CommonCtrl.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		//
		return "index";
	}
	
	// SOUP 방식
	// RESTController의 getMapping 과 기능 같음							POST
	//@RequestMapping(value = "/cmm/create/db") - method 없으면 GET, default임
	@RequestMapping(value = "/cmm/create/db", method = RequestMethod.GET)
	public @ResponseBody Map<?, ?> createDB(){
		HashMap<String, String> map = new HashMap<>();
		map.put("CREATE_DB", SQL.CREATE_DB.toString());
		Consumer<HashMap<String, String>> c = t -> cusMapper.createDB(t);
		c.accept(map);
		map.clear();
		map.put("msg", "SUCCESS");
		return map;
	}
}
