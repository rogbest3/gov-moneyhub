package com.moneyhub.web.tx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneyhub.web.brd.ArticleMapper;
import com.moneyhub.web.cus.Customer;
import com.moneyhub.web.cus.CustomerMapper;
import com.moneyhub.web.exr.ExRate;
import com.moneyhub.web.exr.ExRateMapper;
import com.moneyhub.web.pxy.ArticleProxy;
import com.moneyhub.web.pxy.Box;
import com.moneyhub.web.pxy.CrawlingProxy;
import com.moneyhub.web.pxy.CustomerProxy;
import com.moneyhub.web.pxy.ExrateProxy;
import com.moneyhub.web.pxy.PageProxy;


@Service
public class TxService {	// POJO
	@Autowired TxMapper txmapper;
	@Autowired CrawlingProxy crawler;
	@Autowired CustomerMapper cusmapper;
	@Autowired CustomerProxy manager;
	
	@Autowired ArticleProxy artProxy;
	@Autowired ArticleMapper artMapper;
	
	@Autowired ExrateProxy exProxy;
	@Autowired ExRateMapper exMapper;
	
/*	@SuppressWarnings("unchecked")
	public List<?> crawling(Map<?, ?> paramMap){
		List<String> txServiceList = new ArrayList<>();
		txServiceList.clear();
		txServiceList = (List<String>) crawlingpxy.engine(paramMap);
		return txServiceList;
	}*/
	
	@SuppressWarnings("unchecked")
	public Box<String> crawling(Map<?, ?> paramMap){

		return crawler.engine(paramMap);
	}
	
	@Transactional
	public int registerCustomers() {
		manager.insertCustomers();
		return cusmapper.countCustomers();
	}
	
	// 한번 사용이기 때문에 @Transactional 걸면 늦어짐
	public int truncateCustomers() {

		manager.truncateCustomers();
		
		return cusmapper.countCustomers();
	}
	
	@Transactional
	public String writeArticles() {
		artProxy.insertArticles();
		return artMapper.countArticle();
	}
	@Transactional
	public String writeExrates(String country) {
		exProxy.insertExrates(country);
		return exMapper.countExrate();
	}
}
