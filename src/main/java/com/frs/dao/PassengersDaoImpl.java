package com.frs.dao;

import static com.frs.utils.DBUtils.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frs.pojos.Passengers;

public class PassengersDaoImpl implements PassengersDao {
	private Connection cn;
	private PreparedStatement pst1,pst2,pst3;
	
	public PassengersDaoImpl() throws SQLException {
		cn = getConnection();
		
		pst1 = cn.prepareStatement("select * from passengers");
		pst2 = cn.prepareStatement("insert into passengers value(default,?,?,?)");
		pst3 = cn.prepareStatement("select passenger_id from passengers where passenger_name=? and email=?");
		
	}

	@Override
	public List<Passengers> displayPassengers() throws SQLException {
		
		List<Passengers> reserv = new ArrayList<>();
		
		try(ResultSet rst = pst1.executeQuery()){
			while(rst.next()) {
				reserv.add(new Passengers(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4)));
			}
		}
		return reserv;
	}

	@Override
	public int addPassengers(Passengers newPassenger) throws SQLException {
		pst2.setString(1, newPassenger.getPassengerName());
		pst2.setString(2, newPassenger.getEmail());
		pst2.setString(3, newPassenger.getPhone());
		
		int rowCount = pst2.executeUpdate();
		if(rowCount == 1)
			return getPassengerId(newPassenger.getPassengerName(), newPassenger.getEmail());
		
		return 0;
	}

	@Override
	public int getPassengerId(String name, String email) throws SQLException {
		pst3.setString(1, name);
		pst3.setString(2, email);
		try(ResultSet rs = pst3.executeQuery()){
			if(rs.next())
				return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public void cleanup() throws SQLException {
		if(pst1 != null)
			pst1.close();
	}
	
}
