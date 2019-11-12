package com.moneyhub.web.ahs;

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
public class AccHistory {
	private String accntcd,	acchngcd, cmmt, cmem, cdate, umem, udate;
}
