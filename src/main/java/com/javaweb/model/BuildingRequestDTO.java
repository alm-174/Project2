package com.javaweb.model;

public class BuildingRequestDTO {
	private String name;
	private String ward;
	private String street;
	private Long districtId;
	private Long rentPrice;
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
