package com.frs.dao;

import static com.frs.utils.DBUtils.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.frs.pojos.User;

public class UserDaoImpl implements UserDao{
	private Connection connection;
	private PreparedStatement pst1,pst2,pst3;
	
	public UserDaoImpl() throws SQLException {
		connection = getConnection();
		
		pst1 = connection.prepareStatement("insert into flightUsers value(default,?,?,?,?,?)");
		pst2 = connection.prepareStatement("select * from flightUsers where email=? and password=?");
		pst3 = connection.prepareStatement("select userid from flightUsers where firstname=? and email=?");
		
		System.out.println("User Dao Created");
	}

	@Override
	public String signUp(User newUser) throws SQLException {
		
		pst1.setString(1, newUser.getFirstName());
		pst1.setString(2, newUser.getLastName());
		pst1.setString(3, newUser.getEmail());
		pst1.setString(4, newUser.getPassword());
		pst1.setDate(5, newUser.getDateOfBirth());
		
		int rowCount = pst1.executeUpdate();
		if(rowCount == 1)
			return "User Sucessfully Registered!!";
		
		return "User Registration Failed";
	}

	@Override
	public User signIn(String email, String password) throws SQLException {
		pst2.setString(1, email);
		pst2.setString(2, password);
		
		try(ResultSet rst = pst2.executeQuery()){
			if(rst.next())
				return new User(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getDate(6));
		}
		return null;
	}

	@Override
	public int userId(String fname, String email) throws SQLException {
		pst3.setString(1, fname);
		pst3.setString(2, email);
		try(ResultSet rs = pst3.executeQuery()){
			if(rs.next())
				return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public void cleanUp() throws SQLException {
		if(pst1 != null)
			pst1.close();
		System.out.println("User dao cleaned up!!");
		
	}
	
	
	
}	
