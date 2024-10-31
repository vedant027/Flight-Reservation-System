package com.frs.dao;

import static com.frs.utils.DBUtils.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frs.pojos.Flight;

public class FlightDaoImpl implements FlightDao {
	private Connection connection;
	private PreparedStatement pst1;
	
	public FlightDaoImpl() throws SQLException {
		connection = getConnection();
		
		pst1 = connection.prepareStatement("select * from flight where departure_city=? and arrival_city=? and departure_date=? order by price asc");
		
	}

	@Override
	public List<Flight> availableFlight(String departure_city, String arrival_city, Date departure_date) throws SQLException {
		pst1.setString(1, departure_city);
		pst1.setString(2, arrival_city);
		pst1.setDate(3, departure_date);
		
		List<Flight> flight = new ArrayList<>();
		
		try(ResultSet rst = pst1.executeQuery()){
			while(rst.next()) {
				flight.add(new Flight(rst.getInt(1), rst.getString(2) ,rst.getString(3), rst.getString(4), rst.getString(5), rst.getTime(6), rst.getTime(7),rst.getDouble(8),rst.getDate(9),rst.getInt(10)));
			}
		}
		return flight;
	}

	@Override
	public void cleanUp() throws SQLException {
		if(pst1 != null) {
			pst1.close();
		}
		
	}

	
	
	
}
