package com.moneyhub.web.pxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moneyhub.web.enums.Path;
import com.moneyhub.web.utl.Printer;

@Component("crawler")
public class CrawlingProxy extends Proxy{
	@Autowired Printer p;
	@Autowired Box<String> box;
	
	public Box<String> engine(Map<?, ?> paramMap){	//	정형화되지 않은 data값 반환
		p.accept("키값 : " + paramMap.get("site") + ", 값 : " + paramMap.get("srch"));
		String url = "";
	//	List<String> proxyList = new ArrayList<>();	-> 삭제하고  @Autowired Box<String> box; 사용
		
		switch (string(paramMap.get("srch"))) {
		case "송금" :	//	검색어에 없으면
			for(int i = 0; i<10; i++) {
				box = crawling(Path.CRAWLING_TARGET.toString()+i);	
			}
			break;
		case "환율" :
			crawling(url);
		default:
		//	url = "http://" + paramMap.get("site") + "/";
			crawling("http://" + paramMap.get("site") + "/");
			break;
		}
		return box;
	}

	private Box<String> crawling(String url) {
		p.accept("넘어온 URL : " + url);
		box.clear();
		
		try {
			Document rawData = Jsoup.connect(url).timeout(10*1000).get();
			 Elements title = rawData.select("a[class=noeffect] span[class=ct]"); 
			  Elements content = rawData.select("a[class=noeffect] strong"); 
			  List<String> titleList = new ArrayList<>();
			  List<String> contentList = new ArrayList<>();
			  
			  for(Element e : title) {
				  box.add(e.text());  
			  }
			 
			  System.out.println(titleList); 
			  System.out.println("---------------");
			  for(Element e : content) {
				  contentList.add(e.text());
			//	  art.setContent(e.text());
			  }
			  System.out.println(contentList); 

			  
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return box;
		/*try {
			Connection.Response response = Jsoup.connect(url)
												.method(Connection.Method.GET)
												.execute();
			Document document = response.parse();
		//	String html = document.html();
			String text = document.text();
		//	System.out.println(html);
			p.accept("============================");
			p.accept("크롤링한 텍스트 \n" + text);
			box.add(text);
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}*/
	}
}
