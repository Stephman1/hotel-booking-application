package blackjack;

public class Card {
	private final Suit suit;
    private final Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Value getValue() {
        return this.value;
    }

    public Suit getSuit() {
        return this.suit;
    }

	@Override
	public String toString() {
		return "Suit: " + this.getSuit().toString() + "& Score: " + this.getValue().getScore();
	}
}
