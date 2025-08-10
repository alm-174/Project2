package com.javaweb.service;

import java.util.List;

import com.javaweb.model.BuildingDTO;


public interface BuildingService {
	List<BuildingDTO> findAll(String name, Integer floorArea, String ward, String street, Integer districtId,
			Integer numberOfBasement, String direction, String level, Integer areaFrom, Integer areaTo,
			Integer rentPriceFrom, Integer rentPriceTo, String managerName, String managerPhoneNumber, Integer userId,
			List<String> renttypeCode);
}
