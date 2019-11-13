package com.moneyhub.web.exr;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Lazy
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ExRate {
		
//	private String bsDate, mBuy, mSell, remSend, remReceive, tcBuy, fcbSell, bsRate, befCon, exCommission, dollarRate;
	private String exno, cntcd, bdate, exrate, cmem, cdate, umem, udate;
}
