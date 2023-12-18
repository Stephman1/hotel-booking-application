package blackjack;

import java.util.Collections;
import java.util.Stack;

public class Deck {
	private final Stack<Card> cards = new Stack<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.push(new Card(suit, value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.pop();
    }

    // Additional methods as needed, such as checking if the deck is empty
    public void resupplyDeck() {
    	if (this.deckLow());
    }
    
    public boolean deckLow() {
    	if (this.cards.size() < 20) {
    		return true;
    	}
    	else return false;
    }
}
