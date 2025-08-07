package com.javaweb.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{

	@Autowired //tìm kiếm các interface 
	private BuildingRepository buildingRepository;
	@Override
	public List<BuildingDTO> findAll(String name, Long districtid) {

		List<BuildingEntity> buildingEntities = buildingRepository.findAll(name, districtid);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for (BuildingEntity item : buildingEntities)
		{
			BuildingDTO building = new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet() + ", " + item.getWard());
			building.setNumberOfBasement(item.getNumberOfBasement());
			result.add(building);
			}
		return result;
	}

}
