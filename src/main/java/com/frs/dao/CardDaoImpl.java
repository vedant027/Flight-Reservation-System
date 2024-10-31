package com.frs.dao;

import static com.frs.utils.DBUtils.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frs.pojos.Card;

public class CardDaoImpl implements CardDao {
	private Connection connection;
	private PreparedStatement pst1,pst2,pst3;

	public CardDaoImpl() throws SQLException{
		
		connection = getConnection();
		pst1 = connection.prepareStatement("insert into card(card_number,cardholder_name,exp_date,cvv) values(?,?,?,?)");
		pst2 = connection.prepareStatement("select * from cards");
		pst3 = connection.prepareStatement("select card_id from card where card_number=? and cardholder_name=?");
	}

	@Override
	public int addCard(Card newCard) throws SQLException {
		pst1.setString(1, newCard.getCardNumber());
		pst1.setString(2, newCard.getName());
		pst1.setDate(3, newCard.getExpDate());
		pst1.setString(4, newCard.getCvv());
		
		int rowCount = pst1.executeUpdate();
		if(rowCount == 1) 
			return getCardId(newCard.getCardNumber(), newCard.getName());
		return 0;
	}
	
	

	@Override
	public int getCardId(String cardNo, String cardHolderName) throws SQLException {
		pst3.setString(1, cardNo);
		pst3.setString(2, cardHolderName);
		try(ResultSet rs = pst3.executeQuery()){
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		return 0;
	}

	@Override
	public List<Card> displayCards() throws SQLException {
	    List<Card> cardList = new ArrayList<>();
	    try (ResultSet rst1 = pst2.executeQuery()) {
	        while (rst1.next()) {
	            cardList.add(new Card(
	                rst1.getString("card_number"),
	                rst1.getString("cardholder_name"),
	                rst1.getDate("exp_date"),
	                rst1.getString("cvv")
	            ));
	        }
	    }
	    return cardList;
	}


	@Override
	public void cleanup() throws SQLException {
		if(pst1 != null)
			pst1.close();
		
	}
	
	
}
