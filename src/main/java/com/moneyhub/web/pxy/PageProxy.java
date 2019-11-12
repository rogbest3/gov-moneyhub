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
	private final String FILE_PATH = "C:/Users/rogbe/spring/sts-workspace/moneyhub5/src/main/resources/txt/";
	private int totalCount, startRow, endRow, 
				pageCount, pageNum, pageSize,  startPage, endPage,
				blockCount, blockNum, nextBlock, prevBlock;
	private String search;
	private boolean existPrev, existNext;
	private final int BLOCK_SIZE = 5;

	@Autowired Printer p;
	@Autowired ArticleMapper articleMapper;
	@Autowired ExRate exr;
	@Autowired List<ExRate> exlist;
	@Autowired ExRateMapper exRateMapper;


	
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

/*	public void writeTXT(List<ExRate> exlist) {
		System.out.println(exlist.get(0));
		System.out.println(exlist.get(1));
	//	exlist.forEach( t -> exRateMapper.insertExRate(t));
	//	for( ExRate e : exlist)
			//exRateMapper.insertExRate(e);
	//		System.out.println(e);
	//	map.forEach((k, v) -> System.out.println(String.format("%s : %s", k, v)));
	}*/
	public void rwTXT(){
		String line="";
//		List<ExRate> tmpList = new ArrayList<>();
		BufferedReader reader = null;

		try {
			reader = Files.newBufferedReader(Paths.get(FILE_PATH +"달러_환율변동조회_20191109.txt"));
			Charset.forName("UTF-8");
//			string = reader.readLine();
			
//	bsDate, mBuy, mSell, remSend, remReceive, tcBuy, fcbSell, bsRate, befcon, excommission, dollarRate
			while((line = reader.readLine()) != null) {
//			 	tmpList = new ArrayList<>();
                String[] arr = line.split("\t");
                //배열에서 리스트 반환
                exr.setBsDate(arr[0]);
                exr.setMBuy(arr[1]);
                exr.setMSell(arr[2]);
                exr.setRemSend(arr[3]);
                exr.setRemReceive(arr[4]);
                exr.setTcBuy(arr[5]);
                exr.setFcbSell(arr[6]);
                exr.setBsRate(arr[7]);
                exr.setBefCon(arr[8]);
                exr.setExCommission(arr[9]);
                exr.setDollarRate(arr[10]);

                System.out.println(exr.toString());
                exRateMapper.insertExRate(exr);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
