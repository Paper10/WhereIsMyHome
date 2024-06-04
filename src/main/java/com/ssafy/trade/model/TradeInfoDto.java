package com.ssafy.trade.model;

import com.ssafy.apt.model.AptDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
//house_info_id, price, house_trade_id, 
public class TradeInfoDto extends AptDto {

	private int houseTradeId;
	private int price;
	private String userId;
	private long houseInfoId;
}
