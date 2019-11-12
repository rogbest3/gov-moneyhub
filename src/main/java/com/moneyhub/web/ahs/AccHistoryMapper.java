package com.moneyhub.web.ahs;

import java.util.HashMap;

import org.springframework.stereotype.Repository;
@Repository
public interface AccHistoryMapper {
	public void createAccHistory(HashMap<String, String> paramMap);

}
