package com.moneyhub.web.pxy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class Proxy {
	public int parseInt(String param) {
	//	Function<String, Integer> f = s -> Integer.parseInt(s);
		Function<String, Integer> f = Integer :: parseInt;
		
		return f.apply(param);
	}
	
	public boolean equal(String p1, String p2) {
		BiFunction<String, String, Boolean> f = String :: equals;
		return f.apply(p1, p2);
	}
	
	public int random(int x, int y) {
		BiFunction<Integer, Integer, Integer> f = (i, j) -> (int)(Math.random()*j-i) + i;
		return f.apply(x, y);
	}
	
	public int[] array(int size) {
		Function<Integer, int[]> f = int[] :: new;
		return f.apply(size);
	}

	public String string(Object object) {
		Function<Object, String> f = String :: valueOf;
		return f.apply(object);
	}
	
	public String currentDate() {
		
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	public String currentTime() {
		
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	}
	
	public File makeDir(String t, String u) {
		BiFunction<String, String, File> f = File :: new;
		return f.apply(t, u);
	}
	
	public File makeFile(File t, String u) {
		BiFunction<File, String, File> f = File :: new;
		return f.apply(t, u);
	}
	
}
 
/*class PageProxyDiv extends Proxy{
	
}

class CrawlingProxy extends Proxy{
	
}*/