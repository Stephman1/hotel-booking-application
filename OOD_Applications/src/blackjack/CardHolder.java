package blackjack;

import java.util.ArrayList;
import java.util.List;

public class CardHolder implements ICardHolder {
	protected List<Card> hand = new ArrayList<>();

    @Override
    public void receiveCard(Card card) {
        hand.add(card);
    }

    @Override
    public int calculateScore() {
        // Implement the blackjack scoring rules, including the logic for Aces.
        int score = 0;
        int aceCount = 0;

        for (Card card : hand) {
            if (card.getValue() == Value.ACE) {
                aceCount++;
            }
            score += card.getValue().getScore();
        }

        while (score > 21 && aceCount > 0) {
            score -= 10; // Count Ace as 1 instead of 11
            aceCount--;
        }

        return score;
    }

    @Override
    public boolean isBusted() {
        return calculateScore() > 21;
    }

    @Override
    public void clearHand() {
        hand.clear();
    }
}
