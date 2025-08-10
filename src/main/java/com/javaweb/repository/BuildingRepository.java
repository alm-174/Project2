package com.javaweb.repository;

import java.util.ArrayList;
import java.util.List;

import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll(String name, Integer floorArea, String ward, String street, Integer districtId, Integer numberOfBasement, String direction, String level, Integer areaFrom, Integer areaTo,
	Integer rentPriceFrom, Integer rentPriceTo, String managerName, String managerPhoneNumber, Integer userId, List<String> renttypeCode);

}
