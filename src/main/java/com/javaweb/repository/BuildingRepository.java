package com.javaweb.repository;

import java.util.ArrayList;
import java.util.List;

import com.javaweb.model.request.BuildingRequest;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll(BuildingRequest building);

}
