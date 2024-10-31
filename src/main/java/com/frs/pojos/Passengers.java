package com.frs.pojos;

public class Passengers {
	private int passengerid;
	private String passengerName;
	private String email;
	private String phone;
	
	public Passengers(int passengerid, String passengerName, String email, String phone) {
		super();
		this.passengerid = passengerid;
		this.passengerName = passengerName;
		this.email = email;
		this.phone = phone;
	}

	public Passengers(String passengerName, String email, String phone) {
		super();
		this.passengerName = passengerName;
		this.email = email;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Passengers [passengerid=" + passengerid + ", passengerName=" + passengerName + ", email=" + email
				+ ", phone=" + phone + "]";
	}

	public int getPassengerid() {
		return passengerid;
	}

	public void setPassengerid(int passengerid) {
		this.passengerid = passengerid;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}	