package com.moneyhub.web.acc;

import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
	public void createAccount(HashMap<String, String> paramMap);
}
