package com.ssafy.trade.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.apt.model.HouseTradeDto;
import com.ssafy.trade.model.TradeInfoDto;
import com.ssafy.trade.model.mapper.TradeMapper;

@Service
public class TradeServiceImpl implements TradeService {

	private final TradeMapper mapper;
	
	public TradeServiceImpl(TradeMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void regist(HouseTradeDto houseTradeDto) throws Exception{
		mapper.regist(houseTradeDto);
	}


	@Transactional
	@Override
	public void insertOrUpdateTradeInfo(TradeInfoDto tradeInfoDto) throws Exception {
		 int count = mapper.countTradeInfo(tradeInfoDto);

	        if (count == 1) {
	        	mapper.updateTradeInfo(tradeInfoDto);
	        } else {
	        	mapper.insertTradeInfo(tradeInfoDto);
	        }
	        mapper.updateHouseTrade(tradeInfoDto);
	}

	@Override
	public List<TradeInfoDto> myTradeInfo(String userId) throws Exception {
		return mapper.myTradeInfo(userId);
	}

	@Override
	public int countTradeInfo(TradeInfoDto tradeInfoDto) throws Exception {
		return mapper.countTradeInfo(tradeInfoDto);
	}

	@Override
	public void updateTradeInfo(TradeInfoDto tradeInfoDto) throws Exception {
		mapper.updateTradeInfo(tradeInfoDto);
	}

	@Override
	public void insertTradeInfo(TradeInfoDto tradeInfoDto) throws Exception {
		mapper.insertTradeInfo(tradeInfoDto);
	}

	@Override
	public void updateHouseTrade(TradeInfoDto tradeInfoDto) throws Exception {
		mapper.updateHouseTrade(tradeInfoDto);
	}

	
}
