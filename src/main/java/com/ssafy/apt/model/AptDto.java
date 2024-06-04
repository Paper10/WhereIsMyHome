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
public class AptDto {
	long houseInfoId;
	String apartmentName;
	String floor;
	String area;
	String dong;
	String dealAmount;
	String lat;
	String lng;
	String buildYear;
	String roadName;
	String jibun;
}
