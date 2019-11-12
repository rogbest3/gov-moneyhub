package com.moneyhub.web.adm;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.moneyhub.web.pxy.Trunk;

@Repository
public interface AdminMapper {
	public Admin selectAdminByIdPw(Admin param);
	public void updateAdmin(Admin param);
	public void insertAdmin(Admin param);
	public int adminCount();
	public void deleteAdmin(String param);
	

	
}
