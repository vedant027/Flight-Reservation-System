package com.frs.dao;

import java.sql.SQLException;
import java.util.List;

import com.frs.pojos.Passengers;

public interface PassengersDao {

	List<Passengers> displayPassengers() throws SQLException;
	
	int addPassengers(Passengers newPassenger) throws SQLException;
	
	int getPassengerId(String name, String email) throws SQLException;
	
	void cleanup() throws SQLException;
}
