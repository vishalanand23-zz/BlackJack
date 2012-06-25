package normal;

import java.util.ArrayList;

public class Player {
    ArrayList<Card> cards;

    public Player() {
        this.cards = new ArrayList<Card>();
    }

    public int value() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.value();
        }
        if (sum > 21) sum = -1;
        return sum;
    }

    public boolean isBust() {
        return value() < 0;
    }

    public void deal() {
        Card card = Dealer.deal();
        cards.add(card);
        if (isBust()) throw new PlayerBustException();
    }

    @Override
    public String toString() {
        for (Card card : cards) {
            return card + " ";
        }
        return "";
    }

    public static class PlayerBustException extends RuntimeException {
    }
}
