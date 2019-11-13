package com.moneyhub.web.pxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.moneyhub.web.brd.Article;
import com.moneyhub.web.tx.TxMapper;
import com.sun.org.glassfish.external.arc.Taxonomy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Component("artProxy")
public class ArticleProxy extends Proxy{
	@Autowired Article art;
	@Autowired TxMapper txmapper;
	
	@Autowired CrawlingProxy crawler;
	@Autowired Trunk<String> trunk;
	@Autowired Box<String> box;
	
	@Transactional
	public void makeArticle(int i) {
		try {								//https://kr.fxexchangerate.com/currency-exchange-rates.html   6705232
			Document rawData = Jsoup.connect("http://m.ppomppu.co.kr/new/bbs_list.php?id=freeboard&page=" + i)
									.timeout(10*1000)
									.get();

		      Elements title = rawData.select("a[class=noeffect] span[class=ct]"); 
			  Elements content = rawData.select("a[class=noeffect] strong"); 
			  List<String> titleList = new ArrayList<>();
			  List<String> contentList = new ArrayList<>();
			  
			  for(Element e : title) {
				  titleList.add(e.text());  
			  }
			 
			  System.out.println(titleList); 
			  System.out.println("---------------");
			  for(Element e : content) {
				  contentList.add(e.text());
				  art.setContent(e.text());
			  }
			  System.out.println(contentList); 
			  
			  String cemail = "1";
			  for(int j=0; j<titleList.size(); j++) {
				  
			//	  cemail = txmapper.
				  art.setTitle(titleList.get(j));
				  art.setContent(contentList.get(j));
				  art.setBoardType("게시판");
				  art.setCemail(cemail);
				  txmapper.insertArticle(art);
			  }
			  
			  
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	// 	return new Article(..,makeContent(), ...);
		
	}
/*	public String makeContent() {
		trunk.put(Arrays.asList("site","srch"), Arrays.asList("직접입력", "송금"));
		box = crawler.engine(trunk.get());
		ArrayList<String> t = box.get();
		Collections.shuffle(t);
		return t.get(0);
	}*/
	public String makeEmail() {
		List<String> emailList = Arrays.asList("cpmanck@naver.com", "nl5not2@netian.com", "kdjclr@gmail.com", 
				"jvv1ej2h@daum.net", "pkxgsld5v@nate.com", "gz21m3w@daum.net", "mn76esvn@hanmir.com", "a2xhmhj@yahoo.co.kr", 
				"shqichrb@empal.com", "j69ebnz25s@freechal.com", "tgvt78a@korea.com", "e9wy9u1@hanmail.net");
		return "";
	}
	public Box<String> makeContent() {
		trunk.put(Arrays.asList("site","srch"), Arrays.asList("직접입력", "송금"));

		return crawler.engine(trunk.get());
	}
	
	@Transactional
	public void insertArticles() {
		for(int i = 1; i<=50; i++)
			makeArticle(i);
	}
}
