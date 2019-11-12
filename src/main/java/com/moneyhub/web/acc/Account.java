package com.moneyhub.web.acc;

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
public class Account {
	private String actypcd,	accntcd, bankcd, cmem, cdate, umem, udate;
}
