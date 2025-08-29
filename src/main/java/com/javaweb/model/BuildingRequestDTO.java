package com.javaweb.model;

public class BuildingRequestDTO {
	private Long id;
	private String name;
	private String ward;
	private String street;
	private Long districtId;
	private Long numberOfBasement;
	private Long floorArea;
	private String direction;
	private String level;
	private Long rentPrice;
	private String managerName;
	private String managerPhoneNumber;
	private Long serviceFee;
	public Long getId() {
		return id;
	}
	public Long getNumberOfBasement() {
		return numberOfBasement;
	}
	public Long getFloorArea() {
		return floorArea;
	}
	public String getDirection() {
		return direction;
	}
	public String getLevel() {
		return level;
	}
	public String getManagerName() {
		return managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public Long getServiceFee() {
		return serviceFee;
	}
	public Long getBrokerageFee() {
		return brokerageFee;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}
	public void setServiceFee(Long serviceFee) {
		this.serviceFee = serviceFee;
	}
	public void setBrokerageFee(Long brokerageFee) {
		this.brokerageFee = brokerageFee;
	}
	private Long brokerageFee;
	public String getName() {
		return name;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public Long getRentPrice() {
		return rentPrice;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}

}
