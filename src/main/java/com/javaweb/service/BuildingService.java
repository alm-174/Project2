package com.javaweb.service;

import java.util.List;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.request.BuildingRequest;


public interface BuildingService {
	List<BuildingDTO> findAll(BuildingRequest buildingRequest);
}
