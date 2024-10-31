package com.frs.pojos;

import java.sql.Date;

public class Reservation {
	private int reservation_id;
	private double total_fare;
	private int user_id;
	private int flight_id;
	private int passenger_id;
	private int card_id;
	
	public Reservation(int reservation_id, double total_fare, int user_id, int flight_id,
			int passenger_id, int card_id) {
		super();
		this.reservation_id = reservation_id;
		this.total_fare = total_fare;
		this.user_id = user_id;
		this.flight_id = flight_id;
		this.passenger_id = passenger_id;
		this.card_id = card_id;
	}

	public Reservation(double total_fare, int user_id, int flight_id, int passenger_id,
			int card_id) {
		super();
		this.total_fare = total_fare;
		this.user_id = user_id;
		this.flight_id = flight_id;
		this.passenger_id = passenger_id;
		this.card_id = card_id;
	}

	@Override
	public String toString() {
		return "Reservation [reservation_id=" + reservation_id
				+ ", total_fare=" + total_fare + ", user_id=" + user_id + ", flight_id=" + flight_id + ", passenger_id="
				+ passenger_id + ", card_id=" + card_id + "]";
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public double getTotal_fare() {
		return total_fare;
	}

	public void setTotal_fare(double total_fare) {
		this.total_fare = total_fare;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public int getPassenger_id() {
		return passenger_id;
	}

	public void setPassenger_id(int passenger_id) {
		this.passenger_id = passenger_id;
	}

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	
}


