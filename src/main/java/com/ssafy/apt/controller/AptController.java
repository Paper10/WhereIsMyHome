package com.ssafy.apt.controller;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.apt.model.AptDto;
import com.ssafy.apt.model.HouseTradeDto;
import com.ssafy.apt.model.service.AptService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/apt")
@Tag(name = "아파트 컨트롤러", description = "아파트에..어쩌구 저쩌구...")
public class AptController {

	private final AptService aptService;

	public AptController(AptService aptService) {
		this.aptService = aptService;
	}
	
	@Operation(summary = "아파트 리스트 보기", description = "입력받은 동코드에 해당하는 아파트 리스트를 조회한다")
	@GetMapping("/infolist")
	public ResponseEntity<List<HouseTradeDto>> aptList(@RequestParam("dongCode")@Parameter(description = "동코드(10자리)",required = true) String dongCode) throws Exception {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<List<HouseTradeDto>>(aptService.aptList(dongCode), HttpStatus.OK);
	}

	@Operation(summary = "아파트 리스트 보기", description = "입력받은 동코드에 해당하는 아파트 중 housetrade 테이블에 등록된 아파트를 조회한다.")
	@GetMapping("/tradelist")
	public ResponseEntity<List<HouseTradeDto>> tradeAptList(@RequestParam("dongCode")@Parameter(description = "동코드(10자리)",required = true) String dongCode) throws Exception{	
//		System.out.println(dongCode);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<List<HouseTradeDto>>(aptService.tradeAptList(dongCode), HttpStatus.OK);
		
	}
	@Operation(summary = "실거래 리스트 보기", description = "입력받은 동코드에 해당하는 아파트 중에서 해당 년,월에 거래된 아파트를 조회한다.")
	@GetMapping("/list")
	public ResponseEntity<List<AptDto>> list(@RequestParam("dongCode")@Parameter(description = "동코드(10자리)",required = true) String dongCode
			, @RequestParam("year")@Parameter(description = "년도(4자리)",required = true) String year
			, @RequestParam("month")@Parameter(description = "월(2자리)",required = true) String month) throws Exception{	
		return new ResponseEntity<List<AptDto>>(aptService.list(dongCode,Integer.valueOf(year),Integer.valueOf(month)), HttpStatus.OK);
	}
	@PostMapping("/subway")
	@ResponseBody
	public String subway(@RequestBody String xy, Model model) throws Exception {
//		double x = map.get("x");
//		double y = map.get("y");
//		double x = Double.parseDouble(map.get("x"));
//		double y = Double.parseDouble(map.get("y"));
//		double x = Double.parseDouble(xx);
//		double y = Double.parseDouble(yy);
		StringTokenizer st = new StringTokenizer(xy);
		double x = Double.parseDouble(st.nextToken());
		double y = Double.parseDouble(st.nextToken());
		return aptService.subway(x, y);
		
	}
	@PostMapping("/recommend")
	public ModelAndView recommend(@RequestParam Map<String, String> map) throws Exception {
		ModelAndView mav = new ModelAndView();
		String dongCode = map.get("dong");
		List<AptDto> list = aptService.recommend(dongCode);
		mav.addObject("lists", list);
		mav.setViewName("apt/recommendlist");
		return mav;
	}

}
