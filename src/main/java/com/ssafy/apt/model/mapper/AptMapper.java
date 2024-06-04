package com.ssafy.apt.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.apt.model.AptDto;
import com.ssafy.apt.model.HouseTradeDto;
@Mapper
public interface AptMapper {


	List<AptDto> subway() throws Exception;

	List<AptDto> list(String dongCode, int year, int month) throws Exception;

	List<AptDto> recommend(String dongCode) throws Exception;

	List<HouseTradeDto> tradeAptList(String dongCode) throws SQLException;
	
	List<HouseTradeDto> aptList(String dongCode) throws SQLException;
}
