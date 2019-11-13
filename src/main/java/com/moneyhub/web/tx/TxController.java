package com.moneyhub.web.tx;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneyhub.web.pxy.PageProxy;
import com.moneyhub.web.pxy.Trunk;
import com.moneyhub.web.pxy.Box;
import com.moneyhub.web.pxy.CrawlingProxy;
import com.moneyhub.web.utl.Printer;

@RestController
@Transactional
@RequestMapping("/tx")
public class TxController {
	@Autowired TxService txService;
	@Autowired Printer p;
	@Autowired Trunk<String> trunk;
	@Autowired CrawlingProxy crawler;
	@Autowired Box<String> box;
	
	@GetMapping("/crawling/{site}/{srch}")
	public Map<?, ?> getUrl(@PathVariable String site, 
							@PathVariable String srch){
		HashMap<String, Object> txMap = new HashMap<>();
		p.accept("getUrl 들어옴 - site : " + site + ", srch : " + srch);		
		txMap.clear();
		txMap.put("site", site);
		txMap.put("srch", srch);
		txMap.put("msg", txService.crawling(txMap));
		return txMap;

	}
	
	@GetMapping("/regiser/cus")
	public Map<?, ?> regiserCustomers() {
		int customerCount = txService.registerCustomers();
		trunk.put(Arrays.asList("customerCount"), Arrays.asList(crawler.string(customerCount)));
		return trunk.get();
	}
	
	@GetMapping("/truncate/cus")
	public Map<?, ?> truncateCustomer(){
		int cusCount = txService.truncateCustomers();
		p.accept("서비스 카운팅 : " + cusCount);
		trunk.put(Arrays.asList("cusCount"), Arrays.asList(crawler.string(cusCount)));
		return trunk.get();
	}
	
	@GetMapping("/write/arts")
	public Map<?, ?> writeArticle() {
		String articleCount = txService.writeArticles();
		trunk.put(Arrays.asList("articleCount"), Arrays.asList(articleCount));
		return trunk.get();
	}
	
	@GetMapping("/write/exrate")
	public Map<?, ?> writeExrate() {
		String exrateCount = txService.writeExrates();
		trunk.put(Arrays.asList("exrateCount"), Arrays.asList(exrateCount));
		return trunk.get();
	}
}
