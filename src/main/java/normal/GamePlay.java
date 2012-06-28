package normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static normal.GamePlay.Choices.HIT;
import static normal.GamePlay.Choices.STAY;

public class GamePlay {

    private final DisplayResult displayResult = new DisplayResult();
    private final static Dealer cardDealer = new Dealer();

    public static void main(String[] args) {
        Player gambler = new Player(cardDealer);
        Player dealer = new Player(cardDealer);
        gambler.deal();
        gambler.deal();
        dealer.deal();
        System.out.println(new GamePlay().play(gambler, dealer));
    }

    public DisplayResult.Result play(Player gambler, Player dealer) {
        try {
            while (true) {
                showGameState(gambler, dealer);
                Choices choices = askHitOrStay();
                if (choices.equals(HIT)) {
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

    private Choices askHitOrStay() {
        System.out.println("For stay Enter 'S'/'s'. For hit Enter 'H'/'h'.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = br.readLine().substring(0, 1);
            if (input.compareToIgnoreCase("S") == 0) return STAY;
            if (input.compareToIgnoreCase("H") == 0) return HIT;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new WrongInputException();
    }

    private void showGameState(Player gambler, Player dealer) {
        System.out.println("gambler's value is: " + gambler.value() + " with cards:" + gambler.cards);
        System.out.println("dealer's value is: " + dealer.value() + " with cards:" + dealer.cards);
        System.out.println("------------------------------------------");
    }

    private static class WrongInputException extends RuntimeException {
    }

    public enum Choices {
        HIT, STAY
    }
}
