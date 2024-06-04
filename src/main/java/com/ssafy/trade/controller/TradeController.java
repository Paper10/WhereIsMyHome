package com.ssafy.trade.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.apt.model.HouseTradeDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.trade.model.TradeInfoDto;
import com.ssafy.trade.model.service.TradeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/trade")
@Tag(name = "경매 컨트롤러", description = "경매가..어쩌구 저쩌구...")
public class TradeController {

	private final TradeService tradeService;
	private final MemberService memberService;

	public TradeController(TradeService tradeService, MemberService memberService) {
		this.tradeService = tradeService;
		this.memberService = memberService;
	}

//house_info_id, current_price, expire_date -> insert
//	house_info_id, current_price, expire_date, is_admin, user_id 
	@PostMapping("/regist")
	@Operation(summary = "경매물품 등록", description = "HouseTradeDto 타입의 houseInfoId,"
			+ "currentPrice, expireDate를 입력받아서 housetrade 테이블에 insert한다.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "정상 등록"),
			@ApiResponse(responseCode = "403", description = "권한 없음"),
			@ApiResponse(responseCode = "500", description = "서버 에러") })
	public ResponseEntity<?> regist(@RequestBody HouseTradeDto houseTradeDto) {
		try {
			System.out.println(houseTradeDto.toString());
			String isAdmin = memberService.isAdmin(houseTradeDto.getUserId());
//			System.out.println(Integer.valueOf(isAdmin));
			if (Integer.valueOf(isAdmin) == 1) // 관리자
			{
				tradeService.regist(houseTradeDto);
			} else {
				return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

//	new_price, house_trade_id, user_id
	@PostMapping("/updateprice")
	@Operation(summary = "경매물품 수정", description = "TradeInfoDto 타입의 houseTradeId,"
			+ "newPrice, userId를 입력받아서 tradeinfo테이블에 insert한다."
			+ "이후 tradeinfo 테이블에서 입력받은 houseTradeId와 동일한 데이터 중 price가 가장 큰 값을 가져와 housetrade 테이블에 update한다.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "정상 업데이트"),
			@ApiResponse(responseCode = "500", description = "서버 에러") })
	public ResponseEntity<?> insertOrUpdateTradeInfo(@RequestBody TradeInfoDto tradeInfoDto) {
		System.out.println(tradeInfoDto);
		try {
			tradeService.insertOrUpdateTradeInfo(tradeInfoDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/mypage")
	@Operation(summary = "내 입찰목록 보기", description = "userId를 입력받아서 tradeinfo 테이블에서 입력받은 user_id와 동일한 데이터를 모두 반환한다.")

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "정상 업데이트"),

			@ApiResponse(responseCode = "500", description = "서버 에러") })
	public ResponseEntity<List<TradeInfoDto>> myTradeInfo(@RequestParam("userId") String userId) {
		try {
			return new ResponseEntity<List<TradeInfoDto>>(tradeService.myTradeInfo(userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
