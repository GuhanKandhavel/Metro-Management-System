package metro.persistence;

import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import metro.details.Card;
import metro.details.Station;
import metro.details.TransactionHistory;
import metro.client.MetroClient;
import metro.helper.MySqlConnection;

public class MetroDao implements MetroDaoInterface {

	@Override
	public Collection<TransactionHistory> getTransactionDetails(int cardId)
			throws ClassNotFoundException, SQLException, IOException {
		Connection connection = MySqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT TRANSACTIONID, CARDID, SOURCEID, DESTINATIONID, SWAPINTIME, SWAPOUTTIME,FARE FROM TRANSACTIONDETAILS WHERE CARDID = ? ORDER BY TRANSACTIONID DESC");
        preparedStatement.setInt(1, cardId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Collection<TransactionHistory> transactions = new ArrayList<TransactionHistory>();
        while(resultSet.next())
        {
        	TransactionHistory th = new TransactionHistory();
        	th.setCardId(resultSet.getInt("cardid"));
        	th.setTransactionId(resultSet.getInt("transactionid"));
        	th.setSourceId(resultSet.getInt("sourceid"));
        	th.setDestinationId(resultSet.getInt("destinationid"));
        	th.setSwapInTime(resultSet.getString("swapintime"));
        	th.setSwapOutTime(resultSet.getString("swapoutTime"));
        	th.setFare(resultSet.getInt("fare"));
        	
        	transactions.add(th);
        	
        }
        return transactions;
	}

	
	@Override
	public Collection<Card> getCardDetails(int cardId) throws SQLException, IOException, ClassNotFoundException {
		Connection connection = MySqlConnection.getConnection();
        Collection<Card> cardDetails = new ArrayList<Card>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CARDDETAILS WHERE CARDID=?");
        preparedStatement.setInt(1, cardId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next())
        {
            Card card = new Card();
            card.setCardId(resultSet.getInt("CARDID"));
            card.setBalance(resultSet.getInt("BALANCE"));

            cardDetails.add(card);
        }
        return cardDetails;

	}

	@Override
	public boolean updateBalance(int cardId,int amount) throws SQLException, IOException, ClassNotFoundException {
		Connection connection = MySqlConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE CARDDETAILS SET BALANCE = BALANCE + ? WHERE CARDID=?");
        preparedStatement.setInt(1, amount);
        preparedStatement.setInt(2, cardId);
        int resultSet = preparedStatement.executeUpdate();
        connection.commit();
        return(resultSet>0);
	}

	@Override
	public boolean swipeIn(int cardId, int sourceId) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = MySqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TRANSACTIONDETAILS(cardId,sourceId,swapInTime) VALUES(?,?,SYSDATE())");
        preparedStatement.setInt(1, cardId);
        preparedStatement.setInt(2, sourceId);
 
        int resultSet = preparedStatement.executeUpdate();
        connection.commit();
        System.out.println(resultSet);
		return (resultSet>0);
	}

	@Override
	public Collection<Station> getStationDetails() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = MySqlConnection.getConnection();

        Collection<Station> stations = new ArrayList<Station>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM STATION");
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next())
        {
            Station station = new Station();
            station.setStationId(resultSet.getInt("STATIONID"));
            station.setStationName(resultSet.getString("STATIONNAME"));
            stations.add(station);

        }
        return stations;
		
	}

	@Override
	public boolean createNewCard() throws SQLException, IOException, ClassNotFoundException {
		Connection connection = MySqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CARDDETAILS(balance,issueDate) VALUES(?,SYSDATE())");
        preparedStatement.setInt(1, 100);
        int rows = preparedStatement.executeUpdate();
        connection.commit();
        connection.close();
        return (rows>0);
		
	}

	@Override
	public boolean updateTransactionHistory(int transid, int destinationId, int fare)
			throws SQLException, IOException, ClassNotFoundException {
		Connection connection = MySqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE transactiondetails SET fare = ?,destinationId=?,swapouttime=sysdate() WHERE transactionid = ?");
        preparedStatement.setInt(1, fare);
        preparedStatement.setInt(2, destinationId);
        preparedStatement.setInt(3, transid);
        int affectedRows = preparedStatement.executeUpdate();
        connection.commit();
        return affectedRows > 0;
		
	}

	@Override
	public boolean cardExists(int cardId) throws SQLException, IOException, ClassNotFoundException {
		Connection connection = MySqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT CARDID FROM CARDDETAILS WHERE CARDID=?");
        preparedStatement.setInt(1, cardId);

        ResultSet result = preparedStatement.executeQuery();
        while(result.next()) {
        	return true;
        }
        return false;
	}

	@Override
	public boolean stationExists(int stationId) throws SQLException, IOException, ClassNotFoundException {
		Connection connection = MySqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT STATIONID FROM STATION WHERE STATIONID=?");
        preparedStatement.setInt(1, stationId);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
	}

	@Override
	public Collection<Card> newIssueCard() throws SQLException, IOException, ClassNotFoundException {
		Collection<Card> newCard = new ArrayList<Card>();
		Connection connection = MySqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CARDDETAILS ORDER BY CARDID DESC LIMIT 1");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
        	Card c = new Card();
        	c.setCardId(resultSet.getInt("cardid"));
        	c.setBalance(resultSet.getInt("balance"));
        	newCard.add(c);
        }
		return newCard;
	}

	@Override
	public Collection<TransactionHistory> getLastTransaction(int cardId)
			throws ClassNotFoundException, SQLException, IOException {
		Connection connection = MySqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT TRANSACTIONID, CARDID, SOURCEID, DESTINATIONID, FARE, SWAPINTIME,SWAPOUTTIME FROM TRANSACTIONDETAILS WHERE CARDID = ? ORDER BY TRANSACTIONID DESC LIMIT 1");
        preparedStatement.setInt(1, cardId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Collection<TransactionHistory> transactions = new ArrayList<TransactionHistory>();
        while(resultSet.next())
        {
        	TransactionHistory th = new TransactionHistory();
        	th.setCardId(resultSet.getInt("cardid"));
        	th.setTransactionId(resultSet.getInt("transactionid"));
        	th.setSourceId(resultSet.getInt("sourceid"));
        	th.setDestinationId(resultSet.getInt("destinationid"));
        	th.setSwapInTime(resultSet.getString("swapintime"));
        	th.setSwapOutTime(resultSet.getString("swapoutTime"));
        	th.setFare(resultSet.getInt("fare"));
        	
        	transactions.add(th);
        	
        }
        return transactions;
	
	}


	@Override
	public boolean reduceBalance(int cardId, int fare) throws SQLException, IOException, ClassNotFoundException {
		Connection connection = MySqlConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE CARDDETAILS SET BALANCE = ? WHERE CARDID=?");
        preparedStatement.setInt(1, fare);
        preparedStatement.setInt(2, cardId);
        int resultSet = preparedStatement.executeUpdate();
        connection.commit();
        return(resultSet>0);
	}

}
