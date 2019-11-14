package com.moneyhub.web.brd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.moneyhub.web.cmm.IConsumer;
import com.moneyhub.web.cmm.IFunction;
import com.moneyhub.web.cmm.IPredicate;
import com.moneyhub.web.cmm.ISupplier;
import com.moneyhub.web.cus.Customer;
import com.moneyhub.web.cus.CustomerCtrl;
import com.moneyhub.web.enums.Path;
import com.moneyhub.web.enums.SQL;
import com.moneyhub.web.pxy.PageProxy;
import com.moneyhub.web.pxy.Trunk;
import com.moneyhub.web.tx.TxService;
import com.moneyhub.web.pxy.Box;
import com.moneyhub.web.pxy.FileProxy;
import com.moneyhub.web.utl.Printer;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/articles")	// 노출되기 때문에 약자 사용X
@Log4j
public class ArticleCtrl {
	private static final Logger logger = LoggerFactory.getLogger(ArticleCtrl.class);

	@Autowired Customer customer;
	@Autowired Printer printer;
	@Autowired ArticleMapper articleMapper;
	@Autowired Box<Article> box;
	@Autowired TxService txService;
	@Autowired PageProxy pager;
	@Autowired Trunk<Object> trunk;
	@Autowired FileProxy fileProxy;
	
	@PostMapping("/")
	public Map<?, ?> write(@RequestBody Article param){
		printer.accept("write 들어옴");
		param.setBoardType("게시판");
		
		IConsumer<Article> c = t -> articleMapper.insertArticle(param);
		c.accept(param);
		trunk.put(Arrays.asList("msg"),
					Arrays.asList("SUCCESS"));
		return trunk.get();
	}
	@GetMapping("/page/{pageNo}/size/{pageSize}")
	public Map<?, ?> list(@PathVariable String pageNo, @PathVariable String pageSize ){
		printer.accept("list 들어옴, pageNo : " + pageNo);
		pager.setPageNum(pager.parseInt(pageNo));
		pager.setPageSize(pager.parseInt(pageSize));
		pager.paging();
		
		ISupplier <List<Article>> c = () -> articleMapper.selectAll(pager);
		printer.accept("해당 글목록 : " + c.get());
		
		trunk.put(Arrays.asList("articles", "pxy"),//, "prev", "next"),
				Arrays.asList(c.get(), pager));
		return trunk.get();
	}

	@GetMapping("/count")
	public Map<?, ?> count(){
		printer.accept("count 들어옴");
		ISupplier<String> c =  () -> articleMapper.countArticle();
		/*map.clear();
		map.put("count", c.get());*/
		trunk.put(Arrays.asList("count"), Arrays.asList(c.get()));
		printer.accept("count : " + c.get());
		return trunk.get();
	}

	@PutMapping("/{artSeq}")
	public Map<?, ?> update(@PathVariable String artSeq, @RequestBody Article param) {
		printer.accept("update 들어옴");
		param.setArtSeq(artSeq);
//		param.setBoardType("게시판");
		IConsumer<Article> u =  t -> articleMapper.updateArticle(param);
		u.accept(param);
		printer.accept("update 나감 - "+param.toString());
/*		map.clear();
		map.put("title", "title");*/
		trunk.put(Arrays.asList("title"), Arrays.asList("title"));
		return trunk.get();
	}
	
	@DeleteMapping("/{artSeq}")
	public Map<?, ?> delete(@PathVariable String artSeq) {
		printer.accept("delete로 들어옴");
		IConsumer<String> d = t -> articleMapper.deleteArticle(t);
		d.accept(artSeq);
//		map.clear();
		return trunk.get();
	}
	
	@GetMapping("/create/table")
	public Map<?, ?> createArticle(){
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("CREATE_ARTICLE", SQL.CREATE_ARTICLE.toString());
		printer.accept("테이블 쿼리 생성 : \n" + paramMap.get("CREATE_ARTICLE"));
		IConsumer<HashMap<String, String>> c = t -> articleMapper.createArticle(t);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "SUCCESS");
		
		return paramMap;
	}
	
	@GetMapping("/write")
	public Map<?, ?> writeArticle(){
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("INSERT_ARTICLE", SQL.INSERT_ARTICLE.toString());
		printer.accept("테이블 쿼리 생성 : \n" + paramMap.get("INSERT_ARTICLE"));
		IConsumer<HashMap<String, String>> c = t -> articleMapper.insertArticle1(t);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "SUCCESS");
		
		return paramMap;
	}

	@PostMapping("/fileupload")
	public void fileUpload(MultipartFile[] uploadFile) {
		fileProxy.fileUpload(uploadFile);
	}

}
