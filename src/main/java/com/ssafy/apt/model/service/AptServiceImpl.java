package com.ssafy.apt.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.apt.model.AptDto;
import com.ssafy.apt.model.HouseTradeDto;
import com.ssafy.apt.model.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService{

	private final AptMapper aptMapper;
	
	public AptServiceImpl(AptMapper aptMapper) {
		this.aptMapper = aptMapper;
	}
	
	private List<AptDto> subways = null;

	@Override
	public String subway(double x, double y) throws Exception {
		if (subways == null)
			subways = aptMapper.subway();
		HashMap<Double, String> subs = new HashMap<>();
		double[] fars = new double[subways.size()];
		int idx = 0;
		for (AptDto sub : subways) {
//			dto.setApartmentName(rs.getString("name"));
//			dto.setFloor(rs.getString("line"));
//			dto.setLat(rs.getString("y"));
//			dto.setLng(rs.getString("x"));
			double py = Double.parseDouble(sub.getLat());
			double px = Double.parseDouble(sub.getLng());
			double xx = (x - px) * Math.cos((y + py) / 2);
			double yy = y - py;
			double tfar = Math.sqrt(xx * xx + yy * yy) * 6371e3;
			subs.put(tfar, sub.getFloor() + sub.getApartmentName());
			fars[idx++] = tfar;
		}
		mergeSort(fars, 0, fars.length - 1);
		int far = (int)(fars[0] / 2500);
		String name = subs.get(fars[0]).substring(1);
		String line = subs.get(fars[0]).charAt(0) + "";
		return name + "역(" + line + "):" + far + "분";
	}

	@Override
	public List<AptDto> list(String dongCode, int year, int month) throws Exception {
		return aptMapper.list(dongCode, year, month);
	}

	@Override
	public List<AptDto> recommend(String dongCode) throws Exception {
		List<AptDto> result = new ArrayList<>();
		List<AptDto> list = aptMapper.recommend(dongCode);
		HashMap<String,ArrayList<AptDto>> sorted = new HashMap<>();
		for (AptDto aptDto : list) {
			if(!sorted.containsKey(aptDto.getApartmentName())) { // 
				sorted.put(aptDto.getApartmentName(), new ArrayList<>());	
			}
			sorted.get(aptDto.getApartmentName()).add(aptDto);
		}
		sorted.forEach((key, value) -> {
			AptDto aptDto = value.get(value.size()-1);
			value.sort((v1,v2)->Integer.parseInt(v2.getDealAmount().replace(",","")) - Integer.parseInt(v1.getDealAmount().replace(",","")));
			int t = Integer.parseInt(value.get(0).getDealAmount().replace(",",""));
			if(Integer.parseInt(aptDto.getDealAmount().replace(",","")) * 100 / t <= 70)
				result.add(aptDto);
//			value.sort((v1,v2)->Integer.parseInt(dongCode))
		});
		return result;
	}
	
	
	/////////////////////////////////////
	private static void mergeSort(double[] arr, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}

	private static void merge(double[] arr, int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;

		double[] L = new double[n1];
		double[] R = new double[n2];

		for (int i = 0; i < n1; ++i) {
			L[i] = arr[left + i];
		}
		for (int j = 0; j < n2; ++j) {
			R[j] = arr[mid + 1 + j];
		}

		int i = 0, j = 0;
		int k = left;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	@Override
	public List<HouseTradeDto> tradeAptList(String dongCode) throws Exception {
		return aptMapper.tradeAptList(dongCode);
	}

	@Override
	public List<HouseTradeDto> aptList(String dongCode) throws Exception {
		return aptMapper.aptList(dongCode);
	}

}
