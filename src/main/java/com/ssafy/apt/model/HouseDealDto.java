package com.ssafy.apt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HouseDealDto {
	// 아파트이름, 층, 면적, 법정동, 거래금액
		long no;
		String dealAmount;
		int dealYear;
		int dealMonth;
		int dealDay;
		String area;
		String floor;
		String cancelDealType;
		long aptCode;
		
}
