package com.moneyhub.web.ctx;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
@Configuration
@MapperScan(basePackages = {"com.moneyhub.web"})
@ComponentScan(basePackages = { "com.moneyhub.web" })
//파일 인식 못할때 사용
//@Import({
//	MybatisConfig.class, ServletConfig.class
//})
//
public class RootContext {
	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mariadb://172.168.0.199/moneyhub?serverTimezone=UTC");	//내꺼
	//	hikariConfig.setJdbcUrl("jdbc:mariadb://172.168.0.235/moneyhubdb?serverTimezone=UTC");	//은지씨
	//	hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");							//myhome
	//	hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/moneyhub?serverTimezone=UTC"); //myhome
		hikariConfig.setUsername("moneyhub");
		hikariConfig.setPassword("moneyhub");	
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
}
