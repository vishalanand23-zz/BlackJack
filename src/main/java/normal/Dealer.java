package normal;

public class Dealer {

    public Card deal() {
        double d;
        d = Math.random();
        int suit = (int) Math.ceil(d * 4);
        d = Math.random();
        int number = (int) Math.ceil(d * 13);
        return new Card(number, suit);
    }
}
