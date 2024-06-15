package metro.persistence;

import metro.details.Card;
import metro.details.Station;
import metro.details.TransactionHistory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface MetroDaoInterface {
    public Collection<TransactionHistory> getTransactionDetails(int cardId) throws ClassNotFoundException, SQLException, IOException;
    public Collection<Card> getCardDetails(int cardId) throws SQLException, IOException, ClassNotFoundException ;
    public boolean updateBalance(int cardId,int amount) throws SQLException, IOException, ClassNotFoundException;
    public boolean swipeIn(int cardId,int sourceId) throws SQLException, IOException, ClassNotFoundException ;
    public Collection<Station> getStationDetails() throws SQLException, IOException, ClassNotFoundException ;
    public boolean createNewCard() throws SQLException, IOException, ClassNotFoundException;
    public boolean updateTransactionHistory(int transid,int destinationId,int fare)throws SQLException, IOException, ClassNotFoundException ;
    public boolean cardExists(int cardId) throws SQLException, IOException, ClassNotFoundException  ;
    public boolean stationExists(int stationId) throws SQLException, IOException, ClassNotFoundException  ;
    public Collection<Card> newIssueCard() throws SQLException, IOException, ClassNotFoundException  ;
    public Collection<TransactionHistory> getLastTransaction(int cardId) throws ClassNotFoundException, SQLException, IOException;
    public boolean reduceBalance(int cardId,int fare) throws SQLException, IOException, ClassNotFoundException;

}
