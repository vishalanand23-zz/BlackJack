package normal;

// suit 1 => CLUBS
// suit 2 => DIAMONDS
// suit 3 => HEARTS
// suit 4 => SPADES

public class Card {
    private final int number;
    private final int suit;

    public Card(int number, int suit) {
        verifyNumber(number);
        verifySuit(suit);
        this.number = number;
        this.suit = suit;
    }

    private void verifySuit(int suit) {
        if (suit < 1 || suit > 4) throw new CardGenerationError();
    }

    private void verifyNumber(int number) {
        if (number < 1 || number > 13) throw new CardGenerationError();
    }

    public int getNumber() {
        return number;
    }

    public static class CardGenerationError extends RuntimeException {
    }
}

