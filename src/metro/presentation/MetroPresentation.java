package metro.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import metro.details.Card;
import metro.details.Station;
import metro.details.TransactionHistory;
import metro.client.DisplayExceptions;
import metro.client.MetroClient;
import metro.service.MetroService;
import metro.service.MetroServiceInterface;

public class MetroPresentation implements MetroPresentationInterface{
	MetroServiceInterface metroService = new MetroService();
	MetroClient metroClient = new MetroClient();
	DisplayExceptions display = new DisplayExceptions();
	@Override
	public void showOptions() 
    {
       
        System.out.println("\n\t\t************************************************************\n");
		System.out.println("\t\t*                     1. Swipe in                          *");
        System.out.println("\t\t*                     2. Swipe out                         *");
        System.out.println("\t\t*                     3. Check balance                     *");
        System.out.println("\t\t*                     4. View Transaction Details          *");
        System.out.println("\t\t*                     5. Recharge Card                     *");
        System.out.println("\t\t*                     6. Exit                              *");
        System.out.println("\n\t\t************************************************************\n");
		
	} 

	@Override
	public void performOption(int cardId,int choice) {
		
		Scanner scanner = new Scanner(System.in);
        switch(choice)
        {
            case 1:
            	Collection<Station> stations = new ArrayList<Station>();
			try {
				stations = metroService.getStationDetails();
			} 
			catch (ClassNotFoundException | SQLException | IOException e1) {
					e1.printStackTrace();
			}
                metroClient.displayStations(stations);
                System.out.println("Enter Source Station Id: \n");
                int source = scanner.nextInt();
                try{
                    boolean status = metroService.swipeIn(cardId,source);
                    if(status==true)
                    {
                        System.out.println("Swipe in successfull.\n");
                    }
                    else
                    {
                        System.out.println("*** Enter valid datail!! *** \n");
                    }
                }catch(SQLException| IOException| ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                break;
            case 2:
            	Collection<Station> stations1 = new ArrayList<Station>();
            	try {
            	stations1 = metroService.getStationDetails();
            	}catch(SQLException| IOException| ClassNotFoundException e)
            	{
            		e.printStackTrace();
            	}
            	metroClient.displayStations(stations1);
                System.out.println("Enter Destination Station: \n");
                int destination = scanner.nextInt();
                try{
                    boolean status = metroService.swipeOut(cardId,destination);
                    if(status==true)
                    {
                        System.out.println("Swipe out successfull. Balance Updated \n");
                    }
                    else
                    {
                        System.out.println("Swipe out failed :(. \n");
                    }
                }catch(SQLException| IOException| ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                break;
            case 3:
                int balance=0;
                try{
                    balance = metroService.getBalance(cardId);
                    System.out.println("\n\t\t\t\t ===>  Balance is: " +balance+"  <===");
                }catch(SQLException| IOException| ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                break;

            case 4:
                Collection<TransactionHistory> transactionHistory = new ArrayList<TransactionHistory>();
                try {
                    transactionHistory = metroService.getTransactionDetails(cardId);
                    metroClient.displayTransactions(transactionHistory);
                }catch(SQLException| IOException| ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                break;
            case 5:
                if(isCard(cardId)==true)
                {
                    System.out.println("Enter Amount to recharge: \n");
                    int amount = scanner.nextInt();
                    boolean status = false;
					try {
						status = metroService.rechargeBalance(cardId,amount);
					} catch (ClassNotFoundException | SQLException | IOException e) {
						e.printStackTrace();
					}
                    display.balanceStatus(status);
                }
                break;
            case 6:
                System.out.println("\t\t\t\t------------------------------------------------------");
                System.out.print("\t\t\t\t|                                                    |\n");
            	System.out.print("\t\t\t\t|             Thank You For Travelling with us       |\n");
                System.out.print("\t\t\t\t|                                                    |\n");
                System.out.print("\t\t\t\t------------------------------------------------------\n");
            	System.exit(0);

        }
		
	}

	@Override
	public int newUser() {
		int id=0;
		try {
			Collection<Card> card = new ArrayList<Card>();
            card = metroService.createNewCard();
            for(Card c:card) {
                System.out.println("\n *** New Card Generated. *** \n");
                id=c.getCardId();
                metroClient.displayCard(card);
            }
        }catch(SQLException| IOException| ClassNotFoundException e)
        {
            e.printStackTrace();
        }
		return id;
	}

	@Override
	public boolean isCard(int cardId) {
		boolean status = false;
		try {
			 status = metroService.cardExists(cardId);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return status;
	}

}
