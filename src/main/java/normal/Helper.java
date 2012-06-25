package normal;

public class Helper {

    public boolean isBust(int cards) {
        return cards < 0;
    }

    public Result result(int playerSum, int dealerSum) {
        if (playerSum == -1) return Result.DEALER_WIN;
        else if (dealerSum == -1) return Result.PLAYER_WIN;
        else if (playerSum > dealerSum) return Result.PLAYER_WIN;
        else if (dealerSum > playerSum) return Result.DEALER_WIN;
        else return Result.PUSH;
    }

    public int value(Card[] cards) {
        int sum = 0;
        for (Card card : cards) {
            int number = card.getNumber();
            if (number > 10) number = 10;
            sum += number;
        }
        if (sum > 21) sum = -1;
        return sum;
    }

    public Card deal() {
        double d;
        d = Math.random();
        int suit = (int) Math.ceil(d * 4);
        d = Math.random();
        int number = (int) Math.ceil(d * 13);
        return new Card(number, suit);
    }

    public enum Result {
        PLAYER_WIN, DEALER_WIN, PUSH, PLAYER_BLACK_JACK
    }

    public enum Choices {
        HIT, STAY
    }
}
