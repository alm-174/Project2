package com.javaweb.model.request;

import java.util.List;

public class BuildingRequest {
	private String name;
	private Integer floorArea;
	private Integer numberOfBasement;
	private String ward;
	private String street;
	private Integer districtId;
	private String direction;
	private String level;
	private Integer areaFrom;
	private Integer areaTo;
	private Integer rentPriceFrom;
	private Integer rentPriceTo;
	private String managerName;
	private String managerPhoneNumber;
	private Integer userId;
	private List<String> renttypeCode;
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getDirector() {
		return direction;
	}
	public void setDirector(String director) {
		this.direction = director;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Integer getAreaFrom() {
		return areaFrom;
	}
	public void setAreaFrom(Integer areaFrom) {
		this.areaFrom = areaFrom;
	}
	public Integer getAreaTo() {
		return areaTo;
	}
	public void setAreaTo(Integer areaTo) {
		this.areaTo = areaTo;
	}
	public Integer getRentPriceFrom() {
		return rentPriceFrom;
	}
	public void setRentPriceFrom(Integer rentPriceFrom) {
		this.rentPriceFrom = rentPriceFrom;
	}
	public Integer getRentPriceTo() {
		return rentPriceTo;
	}
	public void setRentPriceTo(Integer rentPriceTo) {
		this.rentPriceTo = rentPriceTo;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<String> getRenttypeCode() {
		return renttypeCode;
	}
	public void setRenttypeCode(List<String> renttypeCode) {
		this.renttypeCode = renttypeCode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
}
