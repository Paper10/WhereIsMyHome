package com.ssafy.trade.model.service;

import java.util.List;

import com.ssafy.apt.model.HouseTradeDto;
import com.ssafy.trade.model.TradeInfoDto;

public interface TradeService {

	void regist(HouseTradeDto houseTradeDto) throws Exception;

	int countTradeInfo(TradeInfoDto tradeInfoDto) throws Exception;
	
	void updateTradeInfo(TradeInfoDto tradeInfoDto) throws Exception;

	void insertTradeInfo(TradeInfoDto tradeInfoDto) throws Exception;

	void updateHouseTrade(TradeInfoDto tradeInfoDto) throws Exception;
	public void insertOrUpdateTradeInfo(TradeInfoDto tradeInfoDto) throws Exception;
	List<TradeInfoDto> myTradeInfo(String userId) throws Exception;


	
}
