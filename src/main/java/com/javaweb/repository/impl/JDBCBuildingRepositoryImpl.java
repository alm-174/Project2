package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCutil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
@PropertySource("classpath:application.properties")

public class JDBCBuildingRepositoryImpl implements BuildingRepository {

	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String USER;
	
	@Value("${spring.datasource.password}")
	private String PASS;
	
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		//Long staffId = Long.parseLong(params.get("staffId").toString());
		Long staffId = buildingSearchBuilder.getStaffId();
		if(staffId != null) {
			sql.append("  inner join estatebasic.assignmentbuilding ab on ab.buildingid = b.id ");
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode != null && typeCode.size() != 0)
		{
			//sql.append("");
			sql.append(" inner join estatebasic.buildingrenttype br on b.id = br.buildingid ");
			sql.append(" inner join estatebasic.renttype r on r.id = br.renttypeid");
		}
		Long rentAreaTo = buildingSearchBuilder.getAreaTo();
		Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
		if(rentAreaTo != null || rentAreaFrom != null)
		{
			sql.append(" inner join estatebasic.rentarea ra on b.id = ra.buildingid ");
		}
	}

	public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		
		
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) { 
				item.setAccessible(true); // bắt buộc có để đọc các field. Cho phép bạn đọc/ghi giá trị của field private, hoặc gọi method private thông qua reflection.
				String fieldName = item.getName();
				if(!fieldName.equals("staffId") && !fieldName.equals("typeCode")
						&& !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
					Object value = item.get(buildingSearchBuilder);
					if(value != null)
					{
						if(item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer"))
						{
							where.append(" AND b." + fieldName + " = " + value);
						}
						else if(item.getType().getName().equals("java.lang.String"))
						{
							where.append(" AND b." + fieldName + " LIKE '%" + value + "%'	");
						}
					}
				}
			}
			
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if(staffId != null) {
			where.append(" AND ab.staffid = " + staffId);
			
		}
		Long rentAreaTo = buildingSearchBuilder.getAreaTo();
		Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
		if(rentAreaTo != null || rentAreaFrom != null)
		{
			where.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE b.id = ra.buildingid ");
			
			if(rentAreaFrom != null)
			{
				where.append(" AND ra.value >=" + rentAreaFrom);
			}
			if(rentAreaTo != null)
			{
				where.append(" AND ra.value <=" + rentAreaTo);
			}
			where.append(") ");
		}
		Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
		Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		if(rentPriceTo != null || rentPriceFrom != null)
		{
			if(rentPriceFrom != null)
			{
				where.append(" AND b.rentprice >=" + rentPriceFrom);
			}
			if(rentPriceTo != null)
			{
				where.append(" AND b.rentprice <=" + rentPriceTo);
			}
		}
		//java 7
		/*if (typeCode != null && typeCode.size() != 0) {
		    List<String> code = new ArrayList<>();
		    for (String item : typeCode) {
		        code.add("'" + item + "'");
		    }
		    where.append(" AND renttype.code IN (" + String.join(",", code) + ") ");
		}*/
		
		//java 8
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if (typeCode != null && typeCode.size() != 0) {
			where.append(" AND(");
			String sql = typeCode.stream().map(it -> "r.code Like" + "'%" + it + "%'").collect(Collectors.joining(" OR "));
			where.append(sql + " ) ");
		}

	}
	
	
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement,  b.floorarea, b.rentprice, "
				+ " b.managername, b.managerphonenumber,  b.servicefee, b.brokeragefee FROM estatebasic.building b ");
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder("WHERE 1=1 ");
		queryNomal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		where.append(" GROUP BY b.id");
		sql.append(where);
		
		
		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString())) { // rs trả về từng hàng của building
			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				//building.setDistrictId(rs.getLong("districtid"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setNumberOfBasement(rs.getLong("numberofbasement"));
				building.setManagerName(rs.getString("managername"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				building.setFloorArea(rs.getLong("floorarea"));
				//building.setAreaFree(null);
				building.setRentPrice(rs.getLong("rentprice"));
				building.setServiceFee(rs.getLong("servicefee"));
				building.setBrokerageFee(rs.getLong("brokeragefee"));
				result.add(building);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Connected database faled ...");
		}
		return result;
	}

}
