package blackjack;

public interface IBlackjackGame {
	void startGame();
    void playDealerTurn();
    void playPlayerTurn();
    String determineOutcome();
}
