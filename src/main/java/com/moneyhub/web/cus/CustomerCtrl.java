package com.moneyhub.web.cus;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moneyhub.web.cmm.IConsumer;
import com.moneyhub.web.cmm.IFunction;
import com.moneyhub.web.cmm.IPredicate;
import com.moneyhub.web.enums.SQL;
import com.moneyhub.web.utl.Printer;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/customers")	// s 복수
@Log4j
public class CustomerCtrl {
	private static final Logger logger = LoggerFactory.getLogger(CustomerCtrl.class);
	@Autowired Map<String, Object> map;
	@Autowired Customer customer;
	@Autowired Printer printer;
	@Autowired CustomerMapper customerMapper;	// 클래식 자바에서는 바로 mapper 연결하면 안되지만 모던자바에선 사용
	
	@GetMapping("/{cemail}/exist")
	public Map<?, ?> existId(@PathVariable String cemail){
		IFunction<String, Integer> f = t -> customerMapper.existEmail(cemail);
		map.clear();
		map.put("msg", f.apply(cemail) == 0 ? "Success" : "Fail");
		printer.accept("map : "+ map);
		return map;
	}
	
	@PostMapping("/")	//	create - 파라미터 없으면
	public Map<?, ?> join(@RequestBody Customer param) {	
		printer.accept("join 들어옴"+param.toString());
		IConsumer<Customer> c = t -> customerMapper.insertCustomer(param);
		c.accept(param); 
		map.clear();
		map.put("msg", "Success");
		return map;
	}
	
	@PostMapping("/{cemail}/login")
	public Customer login(@PathVariable String cemail, @RequestBody Customer param){
		printer.accept("login 들어옴"+ cemail+", "+ param.toString());
		IFunction<Customer, Customer> f = t -> customerMapper.selectCustomerByEmailPw(param);
		return f.apply(param);
	}	
	@GetMapping("/{cemail}")
	public Customer searchCustomerById(@PathVariable String cemail, @RequestBody Customer param) {
		IFunction<Customer, Customer> s = t -> customerMapper.selectCustomerByEmailPw(param);
		return s.apply(param);
	}
	
	@PutMapping("/{cemail}")
	public String updataCustomer(@PathVariable String cemail, @RequestBody Customer param) {
		IConsumer<Customer> c = t -> customerMapper.insertCustomer(param);
		c.accept(param);
		return "Success";
	}
	@DeleteMapping("/{cemail}")
	public String removeCustomer(@PathVariable String cemail, @RequestBody Customer param) {
		IConsumer<Customer> c = t -> customerMapper.insertCustomer(param);
		c.accept(param);
		return "Success";
	}
	
	@GetMapping("/create/table")
	public Map<?, ?> createCustomer(){
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("CREATE_CUSTOMER", SQL.CREATE_CUSTOMER.toString());
		printer.accept("테이블 쿼리 생성 : \n" + paramMap.get("CREATE_CUSTOMER"));
		IConsumer<HashMap<String, String>> c = t -> customerMapper.createCustomer(t);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "SUCCESS");
		
		return paramMap;
	}
	
	@GetMapping("/create/db")
	public Map<?, ?> createDB(){
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("CREATE_DB", SQL.CREATE_DB.toString());
		printer.accept("테이블 쿼리 생성 : \n" + paramMap.get("CREATE_DB"));
		IConsumer<HashMap<String, String>> c = t -> customerMapper.createCustomer(t);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "SUCCESS");
		
		return paramMap;
	}
	

}
