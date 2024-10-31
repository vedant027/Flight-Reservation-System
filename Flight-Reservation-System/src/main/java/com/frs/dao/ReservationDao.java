package com.frs.dao;

import java.sql.SQLException;
import java.util.List;

import com.frs.pojos.Reservation;

public interface ReservationDao {
	
	Reservation displayReservations(int rid) throws SQLException;
	
	int addReservation(double total_fare,int user_id, int fid, int pid, int cardid) throws SQLException;
	
	public int getReservationId(int fid,int pid,int uid,int cid) throws SQLException;
	
	void cleanUp() throws SQLException;
}
