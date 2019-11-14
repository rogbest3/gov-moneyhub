package com.moneyhub.web.pxy;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.moneyhub.web.enums.Path;
import com.moneyhub.web.exr.ExRate;
import com.moneyhub.web.exr.ExRateMapper;
import com.moneyhub.web.utl.Printer;

@Component("exProxy")
public class ExrateProxy extends Proxy{
	
	@Autowired ExRate exr;
	@Autowired List<ExRate> exlist;
	@Autowired ExRateMapper exRateMapper;
	@Autowired Printer p;
	
	/*	public void writeTXT(List<ExRate> exlist) {
	System.out.println(exlist.get(0));
	System.out.println(exlist.get(1));
//	exlist.forEach( t -> exRateMapper.insertExRate(t));
//	for( ExRate e : exlist)
		//exRateMapper.insertExRate(e);
//		System.out.println(e);
//	map.forEach((k, v) -> System.out.println(String.format("%s : %s", k, v)));
}*/
	public String todayDate() {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);
		p.accept(today);*/
		return currentDate();
	}
	@Transactional
	public void rwTXT(){
		String line="";
		BufferedReader reader = null;
		String file = "";
		String cntCd = exr.getCntcd();

		switch (cntCd) {
		case "USD":
			file = "달러_환율변동조회_20191113.tsv";
			break;
		case "CNY":
			file = "위안_환율변동조회_20191113.tsv";
			break;
		case "EUR":
			file = "유로_환율변동조회_20191113.tsv";
			break;
		case "JPY":
			file = "엔화_환율변동조회_20191113.tsv";
			break;
		default:
			break;
		}
		try {
			reader = Files.newBufferedReader(Paths.get(Path.EXRATE_FILE_PATH.toString() +file));
			Charset.forName("UTF-8");

			while((line = reader.readLine()) != null) {
	            String[] arr = line.split("\t");
	            //배열에서 리스트 반환
	/*          exr.setBsDate(arr[0]);
	            exr.setMBuy(arr[1]);
	            exr.setMSell(arr[2]);
	            exr.setRemSend(arr[3]);
	            exr.setRemReceive(arr[4]);
	            exr.setTcBuy(arr[5]);
	            exr.setFcbSell(arr[6]);
	            exr.setBsRate(arr[7]);
	            exr.setBefCon(arr[8]);
	            exr.setExCommission(arr[9]);
	            exr.setDollarRate(arr[10]);*/
	            exr.setExrate(arr[7]);
	            exr.setBdate(arr[0]);
	            exr.setCntcd(cntCd);
	            System.out.println(exr.toString());
	            exRateMapper.insertExRate(exr);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Transactional
	public void insertExrates() {
		rwTXT();
	}
}
