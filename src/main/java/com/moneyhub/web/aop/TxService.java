package com.moneyhub.web.aop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneyhub.web.cus.Customer;
import com.moneyhub.web.cus.CustomerMapper;
import com.moneyhub.web.pxy.CrawlingProxy;
import com.moneyhub.web.pxy.CustomerProxy;
import com.moneyhub.web.pxy.PageProxy;


@Service
public class TxService {	// POJO
	@Autowired TxMapper txmapper;
	@Autowired CrawlingProxy crawlingpxy;
	@Autowired CustomerMapper cusmapper;
	@Autowired CustomerProxy manager;
	
	@SuppressWarnings("unchecked")
	public List<?> crawling(Map<?, ?> paramMap){
		List<String> txServiceList = new ArrayList<>();
		txServiceList.clear();
		txServiceList = (List<String>) crawlingpxy.crawl(paramMap);
		return txServiceList;
	}
	
	@Transactional
	public int registerCustomers() {
		manager.insertCustomers();
		return cusmapper.countCustomers();
	}
	
	// 한번 사용이기 때문에 @Transactional 걸면 늦어짐
	public int truncateCustomers(Map<?, ?> paramMap) {

		manager.truncateCustomers();
		
		return cusmapper.countCustomers();
	}
}
