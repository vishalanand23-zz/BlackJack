package normal;

public class Dealer {

    private RandomNumberGenerator randomNumberGenerator;

    public Dealer() {
    }

    public Dealer(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public Card deal() {
        int suit = randomNumberGenerator.generateInRange(1, 4);
        int number = randomNumberGenerator.generateInRange(1, 13);
        return new Card(number, suit);
    }
}
