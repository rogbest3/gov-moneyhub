package com.moneyhub.web.exr;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ExRateMapper {
	public void insertExRate(ExRate param);
}
