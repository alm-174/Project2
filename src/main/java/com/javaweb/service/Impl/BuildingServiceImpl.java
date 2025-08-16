package com.javaweb.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{

	@Autowired //tìm kiếm các interface 
	private BuildingRepository buildingRepository;
	
	@Autowired 
	private DistrictRepository districtRepository;
	
	@Autowired 
	private RentAreaRepository rentAreaRepository;
	
	
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {

		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, typeCode);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for (BuildingEntity item : buildingEntities)
		{
			BuildingDTO building = new BuildingDTO();
			building.setName(item.getName());
			DistrictEntity districtName = districtRepository.findNameById(item.getDistrictid());
			building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + districtName.getName());
			List<RentAreaEntity> rentAreas = rentAreaRepository.getValueByBuildingId(item.getId());
			String areaResult = rentAreas.stream().map(it-> it.getValue().toString()).collect(Collectors.joining(", "));
			building.setRentArea(areaResult);
			building.setNumberOfBasement(item.getNumberOfBasement());
			result.add(building);
			}
		return result;
	}

}
