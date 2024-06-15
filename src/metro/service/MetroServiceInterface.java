package metro.service;

import metro.details.Card;
import metro.details.Station;
import metro.details.TransactionHistory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface MetroServiceInterface {
    public Collection<TransactionHistory> getTransactionDetails(int cardId) throws SQLException, IOException, ClassNotFoundException;
    public int getBalance(int cardId) throws SQLException, IOException, ClassNotFoundException;
    public boolean swipeIn(int cardId,int sourceId) throws SQLException, IOException, ClassNotFoundException;
    public boolean swipeOut(int cardId,int destinationId) throws SQLException, IOException, ClassNotFoundException;
    public Collection<Station> getStationDetails() throws SQLException, IOException, ClassNotFoundException;
    public Collection<Card> createNewCard() throws SQLException, IOException, ClassNotFoundException;
    public boolean cardExists(int cardId) throws SQLException, IOException, ClassNotFoundException  ;
	public boolean rechargeBalance(int cardId, int amount) throws SQLException, IOException, ClassNotFoundException;
}
