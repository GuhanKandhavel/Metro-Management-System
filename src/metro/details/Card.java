package metro.details;

public class Card {
    private int cardId;
    private int balance;

    public Card(){

    }
    public Card(int cardId, int balance){
        this.cardId=cardId;
        this.balance=balance;

    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
