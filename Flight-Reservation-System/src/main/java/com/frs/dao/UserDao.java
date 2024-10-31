package com.frs.dao;

import java.sql.SQLException;

import com.frs.pojos.User;

public interface UserDao {
	String signUp(User newUser) throws SQLException;
	
	User signIn(String email, String password) throws SQLException;
	
	int userId(String fname, String email) throws SQLException;
	
	void cleanUp() throws SQLException;
}
