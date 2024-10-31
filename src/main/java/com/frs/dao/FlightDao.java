package com.frs.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.frs.pojos.Flight;

public interface FlightDao {
	List<Flight> availableFlight(String departure_city, String arrival_city, Date departure_date) throws SQLException;
	
	void cleanUp() throws SQLException;
}
