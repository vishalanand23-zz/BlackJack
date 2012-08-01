package normal;

import static normal.GetChoice.Choice.HIT;

public class GamePlay {

    private final DisplayResult displayResult;
    private final static Dealer cardDealer = new Dealer(new RandomNumberGenerator());
    private final GetChoice getChoice;

    public GamePlay(GetChoice getChoice, DisplayResult displayResult) {
        this.getChoice = getChoice;
        this.displayResult = displayResult;
    }

    public static void main(String[] args) {
        Player gambler = new Player(cardDealer);
        Player dealer = new Player(cardDealer);
        gambler.deal();
        gambler.deal();
        dealer.deal();
        System.out.println(new GamePlay(new GetChoice(), new DisplayResult()).play(gambler, dealer));
    }

    public DisplayResult.Result play(Player gambler, Player dealer) {
        try {
            while (true) {
                showGameState(gambler, dealer);
                GetChoice.Choice choice = getChoice.askHitOrStay();
                if (choice.equals(HIT)) {
                    gambler.deal();
                    gambler.checkBust();
                    continue;
                }
                while (dealer.value() < 17) {
                    dealer.deal();
                    dealer.checkBust();
                    showGameState(gambler, dealer);
                }
                return displayResult.result(gambler, dealer);
            }
        } catch (Player.PlayerBustException e) {
            showGameState(gambler, dealer);
            return displayResult.result(gambler, dealer);
        }
    }

    private void showGameState(Player gambler, Player dealer) {
        System.out.println("gambler's value is: " + gambler.value() + " with cards:" + gambler.cards);
        System.out.println("dealer's value is: " + dealer.value() + " with cards:" + dealer.cards);
        System.out.println("------------------------------------------");
    }
}
