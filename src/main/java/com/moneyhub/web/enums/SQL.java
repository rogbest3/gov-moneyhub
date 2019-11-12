package com.moneyhub.web.enums;

public enum SQL {
	CREATE_DB, CREATE_CUSTOMER, DROP_CUSTOMER,
	CREATE_ACCOUNT, CREATE_ACCHISTORY, TRUNCATE_CUSTOMER;
	@Override
	public String toString() {
		String result = "";
		switch(this){
		case CREATE_DB :
			result = "CREATE DATABASE MONEYHUB";
			break;
		
		case CREATE_CUSTOMER :
			result = "CREATE TABLE CUSTOMER\r\n" + 
					"(\r\n" + 
					"  CNO       INT            NOT NULL AUTO_INCREMENT ,\r\n" + 
					"  CEMAIL    VARCHAR(25)    NOT NULL                ,\r\n" + 
					"  CPWD      VARCHAR(15)    NOT NULL                ,\r\n" + 
					"  CNAME     VARCHAR(15)    NULL                    ,\r\n" + 
					"  CSTCD     VARCHAR(10)    NULL                    ,\r\n" + 
					"  SDATE     VARCHAR(11)    NULL                    ,\r\n" + 
					"  WDATE     VARCHAR(11)    NULL                    ,\r\n" + 
					"  CMEM      VARCHAR(15)    NULL                    ,\r\n" + 
					"  CDATE     VARCHAR(11)    NULL                    ,\r\n" + 
					"  UMEM      VARCHAR(15)    NULL                    ,\r\n" + 
					"  UDATE     VARCHAR(11)    NULL                    ,\r\n" + 
					"  PRIMARY KEY (CNO))";
			break;
			//ACTYPCD,	ACCNTCD, BANKCD, CMEM, CDATE, UMEM, UDATE
		case CREATE_ACCOUNT :
			result = "CREATE TABLE ACCOUNT(\r\n" + 
					"  ACTYPCD    VARCHAR(10)    NOT NULL 				 ,\r\n" + 
					"  ACCNTCD    VARCHAR(10)    NULL            		 ,\r\n" + 
					"  BANKCD     VARCHAR(10)    NULL             		 ,\r\n" + 
					"  CMEM       VARCHAR(15)    NULL                    ,\r\n" + 
					"  CDATE      VARCHAR(11)    NULL                    ,\r\n" + 
					"  UMEM       VARCHAR(15)    NULL                    ,\r\n" + 
					"  UDATE      VARCHAR(11)    NULL                    ,\r\n" + 
					"  PRIMARY KEY (ACTYPCD))";
			break;
			//ACCNTCD,	ACCHNGCD, CMMT, CMEM, CDATE, UMEM, UDATE
		case CREATE_ACCHISTORY :
			result = "CREATE TABLE ACCHISTORY(\r\n" + 
					"  ACCNTCD    VARCHAR(10)    NOT NULL 				 ,\r\n" + 
					"  ACCHNGCD   VARCHAR(10)    NULL              		 ,\r\n" + 
					"  CMEM       VARCHAR(15)    NULL                    ,\r\n" + 
					"  CDATE      VARCHAR(11)    NULL                    ,\r\n" + 
					"  UMEM       VARCHAR(15)    NULL                    ,\r\n" + 
					"  UDATE      VARCHAR(11)    NULL                    ,\r\n" + 
					"  PRIMARY KEY (ACCNTCD))";
			break;
		case DROP_CUSTOMER :
			result = "DROP TABLE CUSTOMER";
			break;
		
		case TRUNCATE_CUSTOMER :
			result = "TRUNCATE TABLE CUSTOMER";
			break;
		}

		return result;
	}
}
