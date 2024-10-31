package com.frs.pojos;

import java.sql.Date;
import java.sql.Time;

public class Flight {
	private int airline_id;
	private String flight_number;
	private String airline_name;
	private String departure_city;
	private String arrival_city;
	private Time departure_time;
	private Time arrival_time;
	private double price;
	private Date departure_date;
	private int available_seats;
	
	//constructor
	public Flight(int airline_id, String flight_number , String airline_name, String departure_city, String arrival_city, Time departure_time,
			Time arrival_time, double price, Date departure_date, int available_seats) {
		super();
		this.airline_id = airline_id;
		this.flight_number = flight_number;
		this.airline_name = airline_name;
		this.departure_city = departure_city;
		this.arrival_city = arrival_city;
		this.departure_time = departure_time;
		this.arrival_time = arrival_time;
		this.price = price;
		this.departure_date = departure_date;
		this.available_seats = available_seats;
	}

	@Override
	public String toString() {
		return "Flight [airline_id=" + airline_id + ", flight_number=" + flight_number + ", airline_name="
				+ airline_name + ", departure_city=" + departure_city + ", arrival_city=" + arrival_city
				+ ", departure_time=" + departure_time + ", arrival_time=" + arrival_time + ", price=" + price
				+ ", departure_date=" + departure_date + ", available_seats=" + available_seats + "]";
	}


	public int getAirline_id() {
		return airline_id;
	}

	public void setAirline_id(int airline_id) {
		this.airline_id = airline_id;
	}

	public String getAirline_name() {
		return airline_name;
	}

	public void setAirline_name(String airline_name) {
		this.airline_name = airline_name;
	}

	public String getDeparture_city() {
		return departure_city;
	}

	public void setDeparture_city(String departure_city) {
		this.departure_city = departure_city;
	}

	public String getArrival_city() {
		return arrival_city;
	}

	public void setArrival_city(String arrival_city) {
		this.arrival_city = arrival_city;
	}

	public Time getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(Time departure_time) {
		this.departure_time = departure_time;
	}

	public Time getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(Time arrival_time) {
		this.arrival_time = arrival_time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}

	public int getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}

	public String getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}
	
	
}
