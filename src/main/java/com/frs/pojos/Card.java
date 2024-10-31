package com.frs.pojos;

import java.sql.Date;

public class Card {
	private int cardId;
	private String cardNumber;
	private String name;
	private Date expDate;
	private String cvv;
	
	public Card(int cardId, String cardNumber, String name, Date expDate, String cvv) {
		super();
		this.cardId = cardId;
		this.cardNumber = cardNumber;
		this.name = name;
		this.expDate = expDate;
		this.cvv = cvv;
	}

	public Card(String cardNumber, String name, Date expDate, String cvv) {
		super();
		this.cardNumber = cardNumber;
		this.name = name;
		this.expDate = expDate;
		this.cvv = cvv;
	}

	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", cardNumber=" + cardNumber + ", name=" + name + ", expDate=" + expDate
				+ ", cvv=" + cvv + "]";
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	
	
}
