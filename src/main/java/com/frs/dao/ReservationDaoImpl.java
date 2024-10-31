package com.frs.dao;

import static com.frs.utils.DBUtils.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frs.pojos.Reservation;

public class ReservationDaoImpl implements ReservationDao{
	private Connection cn;
	private PreparedStatement pst1,pst2,pst3,pst4,pst5;
	
	public ReservationDaoImpl() throws SQLException {
		cn = getConnection();
		
		pst1 = cn.prepareStatement("select * from reservations where reservation_id=?");
		pst2 = cn.prepareStatement("INSERT INTO reservations (total_fare, user_id, flight_id, passenger_id, card_id) VALUES (?, ?, ?, ?, ?)");
		pst3 = cn.prepareStatement("select reservation_id from reservations where flight_id=? and passenger_id=? and user_id=? and card_id=?");
		pst4 = cn.prepareStatement("SELECT reservation_id, p.passenger_name, f.flight_number, f.departure_city, f.arrival_city, f.departure_date, f.departure_time, f.seats, f.airline_name\r\n"
				+ "FROM reservations r\r\n"
				+ "INNER JOIN passengers p ON r.passenger_id = p.passenger_id\r\n"
				+ "INNER JOIN flight f ON r.flight_id = f.airline_id\r\n"
				+ "WHERE r.reservation_id = ?");
		pst5 = cn.prepareStatement("Update flight set seats=seats-1 where airline_id=?");
	}
	
	public int getReservationId(int fid,int pid,int uid,int cid) throws SQLException {
		pst3.setInt(1, fid);
		pst3.setInt(2, pid);
		pst3.setInt(3, uid);
		pst3.setInt(4, cid);
		
		try(ResultSet rs = pst3.executeQuery()){
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		return 0;
	}

	@Override
	public Reservation displayReservations(int rid) throws SQLException {
		
		pst1.setInt(1, rid);
		
		try(ResultSet rst = pst1.executeQuery()){
			while(rst.next())
				return new Reservation(rst.getInt(1),rst.getDouble(2),rst.getInt(3),rst.getInt(4),rst.getInt(5),rst.getInt(6));
		}
		return null;
	}

	@Override
	public int addReservation(double fare,int uid,int fid, int pid, int cardid) throws SQLException {
		
		pst2.setDouble(1, fare);
		pst2.setInt(2, uid);
		pst2.setInt(3, fid);
		pst2.setInt(4, pid);
		pst2.setInt(5, cardid);
		
		int rows = pst2.executeUpdate();
		pst5.setInt(1, fid);
		int r = pst5.executeUpdate();
		if(rows == 1) {
			return getReservationId(fid,pid,uid,cardid);	
		}
		return 0;
	}


	@Override
	public void cleanUp() throws SQLException {
		if(pst1 != null)
			pst1.close();
		if(pst2 != null)
			pst2.close();
		
	}

}
