package blackjack;

import java.util.Scanner;

public class BlackjackGame implements IBlackjackGame {
	private final Deck deck = new Deck();
    private final Player player = new Player();
    private final Dealer dealer = new Dealer();
    protected Scanner scanner;

    @Override
    public void startGame() {
        deck.shuffle();
        player.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        player.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());

        playPlayerTurn();
        playDealerTurn();
        System.out.println(determineOutcome());
    }

    @Override
    public void playDealerTurn() {
        while (dealer.calculateScore() < 17) {
            dealer.receiveCard(deck.drawCard());
        }
    }

    @Override
    public void playPlayerTurn() {
    	this.scanner = new Scanner(System.in);
    	String nextMove = null;
    	while (!nextMove.equalsIgnoreCase("stand")) {
    		System.out.println("Current score is " + this.player.calculateScore());
    		System.out.println("Hit or stand?");
    		System.out.print("> ");
    		System.out.flush();
    		nextMove = this.scanner.nextLine();
    		if (nextMove.equalsIgnoreCase("stand"));
    		else if (nextMove.equalsIgnoreCase("hit")) {
    			this.player.receiveCard(deck.drawCard());
    			if (this.player.isBusted()) break;
    		} else {
    			System.out.println("Please enter either hit or stand");
    		}
    	}
    }

    @Override
    public String determineOutcome() {
        // Determine outcome logic
        if (player.isBusted()) {
            return "Dealer wins!";
        } else if (dealer.isBusted()) {
            return "Player wins!";
        } else if (player.calculateScore() > dealer.calculateScore()) {
            return "Player wins!";
        } else {
            return "Dealer wins!";
        }
    }
}
