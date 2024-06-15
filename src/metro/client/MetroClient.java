package metro.client;

import metro.details.Card;
import metro.details.Station;
import metro.details.TransactionHistory;
import metro.presentation.MetroPresentation;
import metro.presentation.MetroPresentationInterface;

import java.util.Collection;
import java.util.Scanner;

public class MetroClient {
    public static void main(String[] args){

       
        MetroPresentationInterface metroPresentation=new MetroPresentation();
        Scanner scanner=new Scanner(System.in);
		System.out.println("\t\t\t   ______________________________________________");
		System.out.println("\t\t\t  |                                              |");
		System.out.println("\t\t\t  |     WELCOME TO CHENNAI METRO SERVICES!!!     |");
		System.out.println("\t\t\t  |                                              |");
		System.out.println("\t\t\t  |______________________________________________|");
		System.out.println("\n\t\t\t*** Please select any of the options from below ***\n\n\n");
        while(true) {
		System.out.println("\t\t\t\t*********************************");
        System.out.println("\t\t\t\t*       1. New User             *");
        System.out.println("\t\t\t\t*       2. Existing User        *");
        System.out.println("\t\t\t\t*       3. Exit                 *");
		System.out.println("\t\t\t\t*********************************");
        System.out.println("Enter your choice :");
        int choice = scanner.nextInt();
	    System.out.print("\033[H\033[2J");  
        switch(choice)
        {
            case 1:
            	int userId = metroPresentation.newUser();
            	System.out.println("Your ID : \n"+userId);
            	while(true)
            	{
            		metroPresentation.showOptions();
            		System.out.println("\nEnter choice : \n");
            		int choice1 = scanner.nextInt();
					System.out.print("\033[H\033[2J"); 
            		metroPresentation.performOption(userId,choice1);
            		
            	}
          
            case 2:
            	System.out.println("\nPlease enter your card id:\n");
            	int id = scanner.nextInt();
            	if(metroPresentation.isCard(id)==true)
            	{
            		while(true)
            		{
            			metroPresentation.showOptions();
            			System.out.println("\nEnter choice : \n");
            			int choice2 = scanner.nextInt();
						System.out.print("\033[H\033[2J"); 
            			metroPresentation.performOption(id,choice2);
            		}
            	}
            	else
            	{
            		System.out.println("\n*** Enter a valid card Id ***\n");
            	}
            	break;
            case 3:
				System.out.println("\t\t\t\t____________________________________");
				
				System.out.println("\t\t\t\t|                                  |");
            	System.out.println("\t\t\t\t|                                  |");
				System.out.print("\t\t\t\t|    ***!!Have a nice Day!!***     |\n");
				System.out.print("\t\t\t\t|                                  |\n");
				System.out.print("\t\t\t\t------------------------------------");
            	System.exit(0);
            	
        	}
			
        }
    }
   
    public void displayStations(Collection<Station> stations)
    {
    	for(Station station:stations)
    	{
    		System.out.println(station.getStationId()+"  "+station.getStationName());
    	}
    }
    
    public void displayTransactions(Collection<TransactionHistory> transaction) {
		System.out.println("\nTransactionId\tCardId\tSourceId\tDestinationId\t   SwapInTime\t\t   OutTime\t\t\tFare");
		System.out.println("=====================================================================================================================");
    	for(TransactionHistory trans:transaction) {
    		System.out.println("\n"+"\t" + trans.getTransactionId()+"\t "+trans.getCardId()+"\t "+trans.getSourceId()+"\t\t "+trans.getDestinationId()+"\t\t"+trans.getSwapInTime()+"\t"+trans.getSwapOutTime()+" \t     "+trans.getFare());

    	}
    }

    public void displayCard(Collection<Card> card)
    {
    	for(Card c:card)
    	{
    		System.out.println("\n Your new card details: \n");
    		System.out.println("card Id: "+c.getCardId()+"      "+"Balance: "+c.getBalance());
    	}
    }
}
