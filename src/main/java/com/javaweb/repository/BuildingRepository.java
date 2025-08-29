package com.javaweb.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom{

	void deleteByIdIn(Long[] ids);
	Optional<BuildingEntity> findById(Long id);
	List<BuildingEntity> findByNameContaining(String s);
	List<BuildingEntity> findByNameContainingAndStreet(String name, String street);
	List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
}
