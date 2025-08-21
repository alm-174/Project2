package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;
@Component //chú thích cho spring biết đây là bean
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuldingSearchBuilder (Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
																			.setName(MapUtil.getObject(params, "name", String.class))
																			.setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
																			.setWard(MapUtil.getObject(params, "ward", String.class))
																			.setStreet(MapUtil.getObject(params, "street", String.class))
																	        .setDistrictId(MapUtil.getObject(params, "districtId", Long.class))
																	        .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
																	        .setTypeCode(typeCode)
																	        .setManagerName(MapUtil.getObject(params, "managerName", String.class))
																	        .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
																	        .setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
																	        .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
																	        .setAreaFrom(MapUtil.getObject(params, "areaFrom", Long.class))
																	        .setAreaTo(MapUtil.getObject(params, "areaTo", Long.class))
																	        .setStaffId(MapUtil.getObject(params, "staffId", Long.class))
																			.build();
		return buildingSearchBuilder;
	}
}
