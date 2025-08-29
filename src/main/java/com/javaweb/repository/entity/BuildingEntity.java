package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "building")

public class BuildingEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //tu dong tang dan
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "ward")
	private String ward;
	
	@Column(name = "street")
	private String street;
	
	//@Column(name = "districtid")
	//private Long districtId;
	
	@Column(name = "numberofbasement")
	private Long numberOfBasement;
	
	@Column(name = "managername")
	private String managerName;
	
	@Column(name = "managerphonenumber")
	private String managerPhoneNumber;
	
	@Column(name = "floorarea")
	private Long floorArea;
	
	
	@Column(name = "rentprice")
	private Long rentPrice;
	
	@Column(name = "servicefee")
	private Long serviceFee;
	
	@Column(name = "brokeragefee")
	private Long brokerageFee;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtid")//khi join column thf đồng nghĩa với việc tạo ra 1 comumn mới nên ko cần thêm cột đó
    private DistrictEntity district;
	
	@OneToMany(mappedBy = "building",fetch = FetchType.LAZY)
	private List<RentAreaEntity> buildings = new ArrayList<>();
	
	public List<RentAreaEntity> getItems() {
		return buildings;
	}

	public void setItems(List<RentAreaEntity> buildings) {
		this.buildings = buildings;
	}

	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	/*public Long getDistrictId() {
		return districtId;
	}*/

	public Long getNumberOfBasement() {
		return numberOfBasement;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}

	public Long getFloorArea() {
		return floorArea;
	}

	public Long getRentPrice() {
		return rentPrice;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	/*public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}*/

	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}

	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}

	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}

	public void setServiceFee(Long serviceFee) {
		this.serviceFee = serviceFee;
	}

	public void setBrokerageFee(Long brokerageFee) {
		this.brokerageFee = brokerageFee;
	}
	
	
	
}
