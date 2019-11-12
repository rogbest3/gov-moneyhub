package com.moneyhub.web.exr;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneyhub.web.pxy.PageProxy;
import com.moneyhub.web.pxy.Trunk;
import com.moneyhub.web.pxy.Box;
import com.moneyhub.web.utl.Printer;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/exrate/")
public class ExRateCtrl {
	
	
	@Autowired ExRate exr;
	@Autowired Printer p;
	@Autowired PageProxy pxy;
	@Autowired Trunk<Object> trunk;
	
	@GetMapping("/")
	public Map<?, ?> exRateRW(){
		p.accept("exRateRW 진입");

		pxy.rwTXT();
		trunk.put(Arrays.asList("msg"), Arrays.asList("success"));
		return trunk.get();
	}
}
