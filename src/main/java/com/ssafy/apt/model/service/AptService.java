package com.ssafy.apt.model.service;

import java.util.List;

import com.ssafy.apt.model.AptDto;
import com.ssafy.apt.model.HouseTradeDto;

public interface AptService {

	String subway(double x, double y) throws Exception;

	List<AptDto> list(String dongCode, int year, int month) throws Exception;

	List<AptDto> recommend(String dongCode) throws Exception;

	List<HouseTradeDto> tradeAptList(String dongCode) throws Exception;

	List<HouseTradeDto> aptList(String dongCode) throws Exception;

}
