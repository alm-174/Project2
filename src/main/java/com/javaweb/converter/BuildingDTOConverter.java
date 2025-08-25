package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component //đánh dấu class là 1 bean => không có hàm khởi tạo nên khi gọi phải dùng @Autowired

public class BuildingDTOConverter {
	

	
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTo(BuildingEntity item)
	{
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		building.setAddress(item.getStreet() + ", " + item.getWard() + ", " + item.getDistrict().getName());
		List<RentAreaEntity> rentAreas = item.getItems();
		String areaResult = rentAreas.stream().map(it-> it.getValue().toString()).collect(Collectors.joining(", "));
		building.setRentArea(areaResult);
		return building;
	}
	
}
