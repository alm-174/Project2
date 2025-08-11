package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.request.BuildingRequest;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;

	@PostMapping(value = "/api/building/")
	public List<BuildingDTO> getBuilding(@ModelAttribute BuildingRequest buildingRequest) {

		List<BuildingDTO> result = buildingService.findAll(buildingRequest);
		return result;

	}
	

	/*
	 * public void validDate(BuildingDTO buildingDTO) // throws
	 * FieldRequiredException dùng RuntimeException thì ko cần // dòng này { if
	 * (buildingDTO.getName() == null || buildingDTO.getName().equals("") ||
	 * buildingDTO.getNumberOfBasement() == null) { throw new
	 * FieldRequiredException("name or numberOfBaseMent is null");// tên lỗi ở đây }
	 */

	// xóa phần tử
	@DeleteMapping(value = "/api/building/{id}/{name}")
	// bắt buộc phải có ID
	public void deleteBuilding(@PathVariable Integer id, @PathVariable String name,
			@RequestParam(value = "numberOfBasement", required = false) String ward) {
		System.out.print("Da xoa!!\n");
	}

}
