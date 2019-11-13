package com.moneyhub.web.enums;

public enum SQL {
	CREATE_DB, 
	CREATE_CUSTOMER, DROP_CUSTOMER, TRUNCATE_CUSTOMER,
	CREATE_ACCOUNT, 
	CREATE_ACCHISTORY, 
	CREATE_ARTICLE, INSERT_ARTICLE,
	CREATE_EXRATE, DROP_EXRATE,
	CREATE_RECEIVER, 
	CREATE_FEE;
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
		//ARTSEQ, IMAGE, CID, COMMENTS, MSG, RATING, BOARDTYPE, TITLE, CONTENT	
		case CREATE_ARTICLE :
			result = "create table article (\r\n" + 
					"    ARTSEQ         INT NOT NULL AUTO_INCREMENT,\r\n" + 
					"    IMAGE          VARCHAR(20),\r\n" + 
					"    CEMAIL     	VARCHAR(25),\r\n" + 
					"    COMMENTS  		VARCHAR(100),\r\n" + 
					"    MSG            VARCHAR(20),\r\n" + 
					"    RATING         VARCHAR(20),\r\n" + 
					"    BOARDTYPE   	VARCHAR(20),\r\n" + 
					"    TITLE      	VARCHAR(20),\r\n" + 
					"    CONTENT        VARCHAR(100),\r\n" + 
					"    PRIMARY KEY (ARTSEQ)\r\n" +  
					")";
			break;
		case INSERT_ARTICLE :
			result = " insert into Article (cemail, boardtype, title, content) values (\r\n" + 
					"        	'1', '게시판', 'test1', '1234'\r\n" + 
					"        )";
			break;

		case CREATE_EXRATE :
			// CNTCD, BDATE, EXRATE, CMEM, CDATE, UMEM, UDATE
	/*	result = "create table exrate(\r\n" + 
				"    CCODE int not null auto_increment,\r\n" + 
				"    BSDATE 	VARCHAR(15),\r\n" + 
				"    MBUY 		VARCHAR(10),\r\n" + 
				"    MSELL 		VARCHAR(10),\r\n" + 
				"    REMSEND	VARCHAR(10),\r\n" + 
				"    REMRECEIVE VARCHAR(10),\r\n" + 
				"    TCBUY 		VARCHAR(10),\r\n" + 
				"    FCBSELL	VARCHAR(10),\r\n" + 
				"    BSRATE 	VARCHAR(10),\r\n" + 
				"    BEFCON		 VARCHAR(10),\r\n" + 
				"    EXCOMMISSION VARCHAR(10),\r\n" + 
				"    DOLLARRATE VARCHAR(10),\r\n" + 
				"    PRIMARY KEY (CCODE)\r\n" + 
				")";*/
			result = "CREATE TABLE EXRATE(\r\n" + 
					"   EXNO  		int	auto_increment	NOT NULL ,\r\n" +
					"   CNTCD  		VARCHAR(10) NOT NULL ,\r\n" +
					"   BDATE 		VARCHAR(11)	NULL,\r\n" +
					"	EXRATE 		VARCHAR(10)	NULL,\r\n" + 
					"   CMEM 		VARCHAR(15)	NULL,\r\n" + 
					"   CDATE 		VARCHAR(11)	NULL,\r\n" + 
					"	UMEM 		VARCHAR(15)	NULL,\r\n" + 
					"   UDATE 		VARCHAR(11)	NULL,\r\n" +
					"   PRIMARY KEY (EXNO)\r\n" + 
					")";
		break;
		
		case DROP_EXRATE :
			result = "DROP TABLE EXRATE";
			break;
			
		case CREATE_RECEIVER :
			result = "create table receiver(\r\n" + 
					"    CCODE int not null auto_increment,\r\n" + 
					"    BSDATE 	VARCHAR(15),\r\n" + 
					"    MBUY 		VARCHAR(10),\r\n" + 
					"    MSELL 		VARCHAR(10),\r\n" + 
					"    REMSEND	VARCHAR(10),\r\n" + 
					"    REMRECEIVE VARCHAR(10),\r\n" + 
					"    TCBUY 		VARCHAR(10),\r\n" + 
					"    FCBSELL	VARCHAR(10),\r\n" + 
					"    BSRATE 	VARCHAR(10),\r\n" + 
					"    BEFCON		 VARCHAR(10),\r\n" + 
					"    EXCOMMISSION VARCHAR(10),\r\n" + 
					"    DOLLARRATE VARCHAR(10),\r\n" + 
					"    PRIMARY KEY (CCODE)\r\n" + 
					")";
			break;
		case CREATE_FEE :
			result = "create table fee(\r\n" + 
					"    CCODE int not null auto_increment,\r\n" + 
					"    BSDATE 	VARCHAR(15),\r\n" + 
					"    MBUY 		VARCHAR(10),\r\n" + 
					"    MSELL 		VARCHAR(10),\r\n" + 
					"    REMSEND	VARCHAR(10),\r\n" + 
					"    REMRECEIVE VARCHAR(10),\r\n" + 
					"    TCBUY 		VARCHAR(10),\r\n" + 
					"    FCBSELL	VARCHAR(10),\r\n" + 
					"    BSRATE 	VARCHAR(10),\r\n" + 
					"    BEFCON		 VARCHAR(10),\r\n" + 
					"    EXCOMMISSION VARCHAR(10),\r\n" + 
					"    DOLLARRATE VARCHAR(10),\r\n" + 
					"    PRIMARY KEY (CCODE)\r\n" + 
					")";
			break;
		}

		return result;
	}
}
