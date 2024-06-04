package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {

	int idCheck(String userId) throws SQLException;
	void joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(MemberDto memberDto) throws SQLException;
	void modifyMember(MemberDto memberDto) throws SQLException;
	void deleteMember(String userId) throws SQLException;
	List<MemberDto> listMember(String searchText) throws SQLException;
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userid) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
	MemberDto userInfo(String userId) throws SQLException;
	String isAdmin(String userId) throws SQLException;
}
