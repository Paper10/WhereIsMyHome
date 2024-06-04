package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.member.model.MemberDto;

public interface MemberService {

	int idCheck(String userId) throws SQLException;
	void joinMember(MemberDto memberDto) throws SQLException;
	MemberDto loginMember(MemberDto memberDto) throws SQLException;
	void modifyMember(MemberDto memberDto) throws SQLException;
	void deleteMember(String userId) throws SQLException;
	List<MemberDto> listMember(String searchText)throws SQLException;
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
	MemberDto userInfo(String userId) throws Exception;
	String isAdmin(String userId) throws Exception;
}
