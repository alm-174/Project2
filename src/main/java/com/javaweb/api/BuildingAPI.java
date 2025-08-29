package com.javaweb.api;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

@RestController
@Transactional
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;

	@Autowired
	private BuildingRepository buildingRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping(value = "/api/building/")
	public List<BuildingDTO> getBuilding3(@RequestParam Map<String, Object> params,
			@RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		// Khi 1 data nhieu thi nen nhan dang list
		List<BuildingDTO> result = buildingService.findAll(params, typeCode);
		return result;

	}

	/*
	 * @GetMapping(value = "/api/building/{name}") public BuildingDTO
	 * getBuildingById(@PathVariable String name) { BuildingDTO result = new
	 * BuildingDTO(); List<BuildingEntity> building =
	 * buildingRepository.findByNameContaining(name); return result;
	 * 
	 * }
	 */

	@PostMapping(value = "/api/building/")

	public void creatAndUpdateBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {

		buildingService.createBuilding(buildingRequestDTO);
		System.out.print("ok");

	}

	@DeleteMapping(value = "/api/building/{ids}")
	public void deleteBuilding(@PathVariable Long[] ids) {

		buildingRepository.deleteByIdIn(ids);
		System.out.print("Da xoa!!\n");
	}

}
