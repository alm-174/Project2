package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.model.request.BuildingRequest;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository

public class BuildingRepositoryImpl implements BuildingRepository {

	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";



	@Override
	public List<BuildingEntity> findAll(BuildingRequest buildingRequest) {
		// TODO Auto-generated method stub
		List<BuildingEntity> result = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT b.name, b.numberofbasement, d.name AS districtname, b.ward, b.street, b.floorarea, b.managername, b.managerphonenumber, b.rentprice, b.servicefee, b.brokeragefee "
				+ " FROM  estatebasic.building b "
				+ " inner join estatebasic.rentarea ra on b.id = ra.buildingid "
				+ " inner join estatebasic.district d on d.id = b.districtid "
				+ " inner join estatebasic.assignmentbuilding ab on ab.buildingid = b.id "
				+ " inner join estatebasic.user u on u.id = ab.staffid "
				+ " inner join estatebasic.buildingrenttype br on b.id = br.buildingid "
				+ " inner join estatebasic.renttype r on r.id = br.renttypeid WHERE 1 = 1 ");
		if(buildingRequest.getName() != null && !buildingRequest.getName().equals(""))
		{
			sql.append(" AND b.name like '%" + buildingRequest.getName() + "%' ");
		}
		if(buildingRequest.getFloorArea() != null && buildingRequest.getFloorArea() != 0)
		{
			sql.append(" AND b.floorarea = " + buildingRequest.getFloorArea());
		}
		if(buildingRequest.getWard() != null && !buildingRequest.getWard().equals(""))
		{
			sql.append(" AND b.ward like '%" + buildingRequest.getWard() + "%' ");
		}
		if(buildingRequest.getStreet() != null && !buildingRequest.getStreet().equals(""))
		{
			sql.append(" AND b.street like '%" + buildingRequest.getStreet() + "%' ");
		}
		if(buildingRequest.getDistrictId() != null )
		{
			sql.append(" AND d.id = " + buildingRequest.getDistrictId());
		}
		if(buildingRequest.getNumberOfBasement() != null )
		{
			sql.append(" AND b.numberofbasement = " + buildingRequest.getNumberOfBasement());
		}
		if(buildingRequest.getDirection() != null && !buildingRequest.getDirection().equals(""))
		{
			sql.append(" AND b.direction like '%" + buildingRequest.getDirection() + "%' ");
		}
		if(buildingRequest.getLevel() != null && !buildingRequest.getLevel().equals(""))
		{
			sql.append(" AND b.level like '%" + buildingRequest.getLevel() + "%' ");
		}
		if(buildingRequest.getAreaFrom() != null )
		{
			sql.append(" AND ra.value >= " + buildingRequest.getAreaFrom());
		}
		if(buildingRequest.getAreaTo() != null )
		{
			sql.append(" AND ra.value <= " + buildingRequest.getAreaTo());
		}
		if(buildingRequest.getRentPriceFrom() != null )
		{
			sql.append(" AND b.rentprice >= " + buildingRequest.getRentPriceFrom());
		}
		if(buildingRequest.getRentPriceTo() != null )
		{
			sql.append(" AND b.rentprice <= " + buildingRequest.getRentPriceTo());
		}
		if(buildingRequest.getManagerName() != null && !buildingRequest.getManagerName().equals(""))
		{
			sql.append(" AND b.managername like '%" + buildingRequest.getManagerName() + "%' ");
		}
		if(buildingRequest.getManagerPhoneNumber() != null && !buildingRequest.getManagerPhoneNumber().equals(""))
		{
			sql.append(" AND b.managerphonenumber like '%" + buildingRequest.getManagerPhoneNumber() + "%' ");
		}
		if(buildingRequest.getUserId() != null )
		{
			sql.append(" AND u.id = " + buildingRequest.getUserId());
		}
		if(buildingRequest.getRenttypeCode().size() != 0)
		{
			sql.append(" AND (");
			for(String item : buildingRequest.getRenttypeCode()) 
			{
				if(item != null && item.equals(""))
				{
					sql.append(" r.code = " +"'" + item + "' OR ");
				}
				
			}
			sql.append(" 1 = 1) ");
		}
		sql.append(" GROUP BY b.id");
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) { // rs trả về từng hàng của building
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setDistrict(rs.getString("districtname"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setNumberOfBasement(rs.getInt("numberofbasement"));
				building.setManagerName(rs.getString("managername"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				building.setFloorArea(rs.getInt("floorarea"));
				building.setAreaFree(null);
				building.setRentPrice(rs.getInt("rentprice"));
				building.setServiceFee(rs.getInt("servicefee"));
				building.setBrokerageFee(rs.getInt("brokeragefee"));
				result.add(building);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connected database faled ...");
		}
		return result;
	}

}
