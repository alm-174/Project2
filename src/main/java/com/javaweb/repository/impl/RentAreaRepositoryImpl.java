package com.javaweb.repository.impl;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionJDBCutil;


@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{

	@Override
	public List<RentAreaEntity> getValueByBuildingId(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM estatebasic.rentarea ra WHERE ra.buildingid = " + id;
		List<RentAreaEntity> rentAreas = new ArrayList<>();
		try (Connection conn = ConnectionJDBCutil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){ // rs trả về từng hàng của building
			while (rs.next()) {
				RentAreaEntity areaEntity = new RentAreaEntity();
				areaEntity.setValue(rs.getString("value"));
			}
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.print("Connected database faled ...");
			}
		return rentAreas;
	}
	
}
