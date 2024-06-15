package metro.details;

public class TransactionHistory {
    private int transactionId;
    private int cardId;
    private int sourceId;
    private int destinationId;
    private String swapInTime;
    private String swapOutTime;
    private int fare;

    public TransactionHistory(){

    }
    public TransactionHistory(int transactionId,int cardId,int sourceId,int destinationId,String swapInTime,String swapOutTime)
    {
        this.transactionId=transactionId;
        this.cardId=cardId;
        this.sourceId=sourceId;
        this.destinationId=destinationId;
        this.swapInTime=swapInTime;
        this.swapOutTime=swapOutTime;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public String getSwapInTime() {
        return swapInTime;
    }

    public void setSwapInTime(String swapInTime) {
        this.swapInTime = swapInTime;
    }

    public String getSwapOutTime() {
        return swapOutTime;
    }

    public void setSwapOutTime(String swapOutTime) {
        this.swapOutTime = swapOutTime;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }


}
