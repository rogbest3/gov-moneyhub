package com.moneyhub.web.tx;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moneyhub.web.brd.Article;
import com.moneyhub.web.cus.Customer;

@Repository
public interface TxMapper {
	@Insert("INSERT INTO CUSTOMER (CNO, CEMAIL, CPWD, CNAME, CSTCD, SDATE, WDATE, CMEM, CDATE, UMEM, UDATE) \n" + 
			"VALUES (\n" + 
			"  #{cno}, #{cemail}, #{cpwd}, #{cname}, #{cstcd}, #{sdate}, #{wdate}, #{cmem}, #{cdate}, #{umem}, #{udate})")
	public int insertCustomer(Customer param);
	
	@Insert(" SELECT COUNT(*) FROM CUSTOMER\n" + 
			"		WHERE CEMAIL LIKE #{cemail}")
	public int existEmail(String cemail);
	
	@Insert("insert into Article (cemail, boardtype, title, content) values (\n" + 
			"            #{cemail}, #{boardType}, #{title}, #{content})")
	public int insertArticle(Article param);
	
	@Insert("SELECT CEMAIL FROM ARTICLE\n" +
			"		WHERE ARTSEQ LIKE #{artseq}")
	public String selectArticleByEmail(String seq);
}
