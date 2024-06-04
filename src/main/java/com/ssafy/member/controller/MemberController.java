package com.ssafy.member.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.ssafy.mail.MailManager;
import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.util.JWTUtil;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/user")
@Slf4j
public class MemberController {
	private MemberService memberService;
	private final JWTUtil jwtUtil;

	public MemberController(MemberService memberService, JWTUtil jwtUtil) {
		super();
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}

//	@Hidden
//	@Operation(hidden = true)
	@GetMapping("/{userid}")
	@ResponseBody
	public String idCheck(@PathVariable("userid") String userId) throws Exception {
		log.debug("idCheck userid : {}", userId);
		int cnt = memberService.idCheck(userId);
		return cnt + "";
	}
	
	
	@Operation(summary = "회원가입111")
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody MemberDto memberDto, Model model) {
		log.debug("memberDto info : {}", memberDto);
		try {
			memberService.joinMember(memberDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "로그인", description = "아이디, 비밀번호를 받아서 로그인한다.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @Parameter(description = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		log.debug("login user : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDto loginUser = memberService.loginMember(memberDto);
			if (loginUser != null && BCrypt.checkpw(memberDto.getUserPwd(), loginUser.getUserPwd())) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);

//				발급받은 refresh token 을 DB에 저장.
				memberService.saveRefreshToken(loginUser.getUserId(), refreshToken);

//				JSON 으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}

		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "회원정보 수정", description = "현재 로그인한 회원의 정보를 수정한다.")
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody MemberDto memberDto, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
		try {
			memberService.modifyMember(memberDto);
			System.out.println("수정 성공");
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			log.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		} else {
		log.error("사용 불가능 토큰!!!");
		status = HttpStatus.UNAUTHORIZED;
	}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

//	@Operation(summary = "회원정보 삭제", description = "현재 로그인한 회원의 정보를 삭제한다.")
//	@DeleteMapping("/delete")
//	public ResponseEntity<?> delete(@RequestBody String userId, HttpServletRequest request) throws Exception {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
//		try {
//			memberService.deleteMember(userId);
//			System.out.println("삭제 성공");
//		} catch (Exception e) {
//			log.error("정보조회 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		} else {
//		log.error("사용 불가능 토큰!!!");
//		status = HttpStatus.UNAUTHORIZED;
//	}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}

	@Operation(summary = "회원인증", description = "회원 정보를 담은 Token 을 반환한다.")
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @Parameter(description = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userId);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "로그아웃", description = "회원 정보를 담은 Token 을 제거한다.")
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(
			@PathVariable("userId") @Parameter(description = "로그아웃 할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "관리자용 회원정보 전체 보기", description = "searchText가 null인 경우에는 전체 회원의 회원정보를 리턴하고,"
			+ "searchText가 null이 아닌 경우에는 searchText에 해당하는 회원 정보를 찾아 리턴한다")
	@PostMapping("/userlist")
	public ResponseEntity<?> userlist(@RequestParam(name = "searchText", required = false) String searchText)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			List<MemberDto> list = memberService.listMember(searchText);
			resultMap.put("lists", list);
		} catch (Exception e) {
			log.error("조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@Operation(summary = "회원정보 보기", description = "현재 로그인한 회원의 아이디를 받아서 해당 회원의 아이디에 해당하는 memberDto 객체를 리턴한다.")
	@GetMapping("/profile")
	public ResponseEntity<Map<String, Object>> profile(@RequestParam(name = "userId", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
//		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
		try {
			MemberDto memberDto = memberService.userInfo(userId);
			resultMap.put("memberDto", memberDto);
		} catch (Exception e) {
			log.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
//		} else {
//		log.error("사용 불가능 토큰!!!");
//		status = HttpStatus.UNAUTHORIZED;
//	}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	  
	//@PostMapping("/findpassword")
	
	  
	@Operation(summary = "Access Token 재발급", description = "만료된 access token 을 재발급 받는다.")
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 access token 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("refresh token 도 사용 불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
