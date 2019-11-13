package com.moneyhub.web.exr;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ExRateMapper {
	public void insertExRate(ExRate param);
	public void createExrate(HashMap<String, String> paramMap);
	public void deleteExrate(HashMap<String, String> paramMap);
	public String countExrate();
	
	
}
