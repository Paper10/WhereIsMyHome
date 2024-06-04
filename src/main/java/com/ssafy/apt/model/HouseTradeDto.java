package com.ssafy.apt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HouseTradeDto extends AptDto {

	public HouseTradeDto() {
		super();
	}
	private int houseTradeId;
	private long houseInfoId;
	private String userId;
	private int currentPrice;
	private String expireDate;
}
