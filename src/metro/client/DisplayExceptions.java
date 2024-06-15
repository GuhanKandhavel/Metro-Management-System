package metro.client;

public class DisplayExceptions {

	public void noStation() {
		System.out.println("*** Invalid station details. Enter valid details ****. \n");
	}
	public void transactionException() {
		System.out.println("*** Warning: Do not Swipe out without Swiping in. *** \n");
	}
	
	public void updateBalanceException()
	{
		System.out.println("*** Balance Updated failed *** \n");
	}
	
	public void insufficientBalance()
	{
		System.out.println("*** Insufficient Balance!!! *** \n");
	}
	
	public void balanceStatus(boolean status)
    {
    	if(status == false)
    	{
    		System.out.println("*** Recharge failed!!! ***\n");
    	}
    	else
    	{
    		System.out.println("*** Balance updated!!! ***\n");
    	}
    }
	
	public void negativeAmountException()
	{
		System.out.println("***Amount can not be negative ***\n");
	}
}
