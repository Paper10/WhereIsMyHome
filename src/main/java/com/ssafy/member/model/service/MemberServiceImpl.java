package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
//	@Autowired
//	private SqlSession sqlSession;
	
	private final MemberMapper memberMapper;

	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public int idCheck(String userId) throws SQLException {
//		return sqlSession.getMapper(MemberMapper.class).idCheck(userId);
		return memberMapper.idCheck(userId);
	}

	@Override
	public void joinMember(MemberDto memberDto) throws SQLException {
//		sqlSession.getMapper(MemberMapper.class).joinMember(memberDto);
		String userPwd = memberDto.getUserPwd();
		userPwd = BCrypt.hashpw(userPwd, BCrypt.gensalt());
		memberDto.setUserPwd(userPwd);
		memberMapper.joinMember(memberDto);
	}
	@Override
	public MemberDto loginMember(MemberDto memberDto) throws SQLException {
		return memberMapper.loginMember(memberDto);
	}

	@Override
	public void modifyMember(MemberDto memberDto) throws SQLException {
		String userPwd = memberDto.getUserPwd();
		userPwd = BCrypt.hashpw(userPwd, BCrypt.gensalt());
		memberDto.setUserPwd(userPwd);
		memberMapper.modifyMember(memberDto);
	}

	@Override
	public void deleteMember(String userId) throws SQLException {
		memberMapper.deleteMember(userId);
	}

	@Override
	public List<MemberDto> listMember(String searchText) throws SQLException {
		return memberMapper.listMember(searchText);
		
	}

	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return memberMapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}

	@Override
	public MemberDto userInfo(String userId) throws Exception {
		return memberMapper.userInfo(userId);
	}

	@Override
	public String isAdmin(String userId) throws Exception {
		return memberMapper.isAdmin(userId);
	}
	
	

}
