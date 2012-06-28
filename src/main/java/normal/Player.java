package normal;

import java.util.ArrayList;

public class Player {
    final ArrayList<Card> cards;
    private final Dealer dealer;

    public Player(Dealer dealer) {
        this.dealer = dealer;
        this.cards = new ArrayList<Card>();
    }

    public int value() {
        int sum = 0;
        int countAces = 0;
        for (Card card : cards) {
            sum += card.value();
            if (card.value() == 1) countAces++;
        }
        if (sum > 21) return -1;
        if (sum < 12 && countAces > 0) sum += 10;
        return sum;
    }

    public boolean isBust() {
        return value() < 0;
    }

    public void deal() {
        Card card = dealer.deal();
        cards.add(card);
        if (isBust()) throw new PlayerBustException();
    }

    public static class PlayerBustException extends RuntimeException {
    }
}
