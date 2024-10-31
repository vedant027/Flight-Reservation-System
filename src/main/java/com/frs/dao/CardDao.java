package com.frs.dao;

import java.sql.SQLException;
import java.util.List;

import com.frs.pojos.Card;

public interface CardDao {
	
	int addCard(Card newCard) throws SQLException;
	
	int getCardId(String cardNo, String cardHolderName) throws SQLException;
	
	List<Card> displayCards() throws SQLException;
	
	void cleanup() throws SQLException;
}
