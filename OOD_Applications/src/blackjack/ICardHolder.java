package blackjack;

public interface ICardHolder {
	void receiveCard(Card card);
    int calculateScore();
    boolean isBusted();
    void clearHand();
}
