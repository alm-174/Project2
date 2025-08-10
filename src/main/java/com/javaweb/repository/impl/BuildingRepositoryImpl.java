package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository

public class BuildingRepositoryImpl implements BuildingRepository {

	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "123456";


	@Override
	public List<BuildingEntity> findAll(String name, Integer floorArea, String ward, String street, Integer districtId,
			Integer numberOfBasement, String direction, String level, Integer areaFrom, Integer areaTo,
			Integer rentPriceFrom, Integer rentPriceTo, String managerName, String managerPhoneNumber, Integer userId,
			List<String> renttypeCode) {
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
		if(name != null && !name.equals(""))
		{
			sql.append(" AND b.name like '%" + name + "%' ");
		}
		if(floorArea != null && floorArea != 0)
		{
			sql.append(" AND b.floorarea = " + floorArea);
		}
		if(ward != null && !ward.equals(""))
		{
			sql.append(" AND b.ward like '%" + ward + "%' ");
		}
		if(street != null && !street.equals(""))
		{
			sql.append(" AND b.street like '%" + street + "%' ");
		}
		if(districtId != null )
		{
			sql.append(" AND d.id = " + districtId);
		}
		if(numberOfBasement != null )
		{
			sql.append(" AND b.numberofbasement = " + numberOfBasement);
		}
		if(direction != null && !direction.equals(""))
		{
			sql.append(" AND b.direction like '%" + direction + "%' ");
		}
		if(level != null && !level.equals(""))
		{
			sql.append(" AND b.level like '%" + level + "%' ");
		}
		if(areaFrom != null )
		{
			sql.append(" AND ra.value >= " + areaFrom);
		}
		if(areaTo != null )
		{
			sql.append(" AND ra.value <= " + areaTo);
		}
		if(rentPriceFrom != null )
		{
			sql.append(" AND b.rentprice >= " + rentPriceFrom);
		}
		if(rentPriceTo != null )
		{
			sql.append(" AND b.rentprice <= " + rentPriceTo);
		}
		if(managerName != null && !managerName.equals(""))
		{
			sql.append(" AND b.managername like '%" + managerName + "%' ");
		}
		if(managerPhoneNumber != null && !managerPhoneNumber.equals(""))
		{
			sql.append(" AND b.managerphonenumber like '%" + managerPhoneNumber + "%' ");
		}
		if(userId != null )
		{
			sql.append(" AND u.id = " + userId);
		}
		if(renttypeCode != null)
		{
			sql.append(" AND (");
			for(String item : renttypeCode) 
			{
				if(item != null && item.equals(""))
				{
					sql.append(" r.code = " +"'" + item + "' OR ");
				}
				
			}
			sql.append(" 1 = 0) ");
		}
		//sql.append(" GROUP BY b.id");
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
