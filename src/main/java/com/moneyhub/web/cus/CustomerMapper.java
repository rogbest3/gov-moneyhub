package com.moneyhub.web.cus;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.moneyhub.web.pxy.Trunk;

@Repository
public interface CustomerMapper {
	public void insertCustomer(Customer param);
	public Customer selectCustomerByEmailPw(Customer param);
	public int existEmail(String cemail);
	public int countCustomers();
	

	public void createDB(HashMap<String, String> paramMap);
	public void createCustomer(HashMap<String, String> paramMap);
	
	public void truncateCustomer(HashMap<String, String> paramMap);
	
/*	public void createDB(Trunk<? extends Customer> paramMap);
	public void createCustomer(Trunk<? extends Customer> paramMap);
*/}
