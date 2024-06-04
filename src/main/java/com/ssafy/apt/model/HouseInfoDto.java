package com.ssafy.apt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HouseInfoDto {
	long aptCode;
	int buildYear;
	String roadName;
	String roadNameBonbun;
	String roadNameSeq;
	String roadNameBasementCode;
	String dong;
	String bonbun;
	String bubun;
	String sigunguCode;
	String eubmyundongCode;
	String dongCode;
	String landCode;
	String apartmentName;
	String jibun;
	String lng;
	String lat;
	
}
