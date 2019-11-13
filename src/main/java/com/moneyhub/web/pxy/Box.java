package com.moneyhub.web.pxy;

import java.util.ArrayList;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component @Data @Lazy
public class Box<T> {	//	list

	private ArrayList<T> list;
	
	// box.clear() 에러 보안
	public Box() { list = new ArrayList<T>(); }
	
	public void add(T item){
		list = new ArrayList<>();
		list.add(item);
	}
	public T get(int i) { return list.get(i); }
	
	public ArrayList<T> get() { return list; }
	
	public int size() { return list.size(); }
	
	public String toString() { return list.toString(); }
	
	public void clear() { list.clear();}
}
