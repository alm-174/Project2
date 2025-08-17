package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component //đánh dấu class là 1 bean => không có hàm khởi tạo nên khi gọi phải dùng @Autowired

public class BuildingDTOConverter {
	
	@Autowired 
	private DistrictRepository districtRepository;
	
	@Autowired 
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTo(BuildingEntity item)
	{
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		
		DistrictEntity districtName = districtRepository.findNameById(item.getDistrictid());
		building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + districtName.getName());
		List<RentAreaEntity> rentAreas = rentAreaRepository.getValueByBuildingId(item.getId());
		String areaResult = rentAreas.stream().map(it-> it.getValue().toString()).collect(Collectors.joining(", "));
		building.setRentArea(areaResult);
		return building;
	}
	
}
