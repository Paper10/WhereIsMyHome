package com.ssafy.trade.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.apt.model.HouseTradeDto;
import com.ssafy.trade.model.TradeInfoDto;

@Mapper
public interface TradeMapper {

	void regist(HouseTradeDto houseTradeDto) throws SQLException;

	int countTradeInfo(TradeInfoDto tradeInfoDto) throws SQLException;
	
	void updateTradeInfo(TradeInfoDto tradeInfoDto) throws SQLException;

	void insertTradeInfo(TradeInfoDto tradeInfoDto) throws SQLException;

	void updateHouseTrade(TradeInfoDto tradeInfoDto) throws SQLException;
	
	List<TradeInfoDto> myTradeInfo(String userId) throws SQLException;
}
