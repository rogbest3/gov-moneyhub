package com.moneyhub.web.pxy;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.moneyhub.web.brd.ArticleMapper;
import com.moneyhub.web.cmm.ISupplier;
import com.moneyhub.web.cus.Customer;
import com.moneyhub.web.cus.CustomerMapper;
import com.moneyhub.web.exr.ExRate;
import com.moneyhub.web.exr.ExRateMapper;
import com.moneyhub.web.utl.Printer;

import lombok.Data;

//@Component @Data @Lazy - 부모가 가지고 있으면 자식도 걸림
@Lazy
@Data
@Component("pager")
public class PageProxy extends Proxy{

	private int totalCount, startRow, endRow, 
				pageCount, pageNum, pageSize,  startPage, endPage,
				blockCount, blockNum, nextBlock, prevBlock;
	private String search;
	private boolean existPrev, existNext;
	private final int BLOCK_SIZE = 5;

	@Autowired Printer p;
	@Autowired ArticleMapper articleMapper;

	@SuppressWarnings("unused")
	public void paging() {
		ISupplier<String> s = ()-> articleMapper.countArticle();
		// row 기준 - page 구성
		totalCount = Integer.parseInt(s.get());
		pageCount = ( totalCount % pageSize == 0 ) 
						? totalCount / pageSize 
						:  totalCount / pageSize + 1;
		startRow = ( pageNum - 1 ) * pageSize;
		endRow = pageNum == pageCount ?	totalCount - 1 : startRow + pageSize - 1;
		
		// page 기준 - block 구성
		blockCount = ( pageCount % BLOCK_SIZE == 0 ) 
						? pageCount / BLOCK_SIZE 
						: pageCount / BLOCK_SIZE + 1;	
		blockNum = ( pageNum - 1 ) / BLOCK_SIZE;
		startPage = blockNum * BLOCK_SIZE + 1;
		endPage = blockNum != ( blockCount - 1) ? ( blockNum + 1 ) * BLOCK_SIZE : pageCount ;
		
		existPrev = blockNum > 0;
		existNext = blockNum < ( blockCount - 1);
		
		nextBlock = startPage + BLOCK_SIZE;
		prevBlock = startPage - BLOCK_SIZE;
	}




}
