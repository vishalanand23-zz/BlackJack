package normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static normal.GamePlay.Choices.HIT;
import static normal.GamePlay.Choices.STAY;

public class GamePlay {

    private final DisplayResult displayResult = new DisplayResult();

    public static void main(String[] args) {
        Player gambler = new Player();
        Player dealer = new Player();
        gambler.deal();
        gambler.deal();
        dealer.deal();
        System.out.println(new GamePlay().play(gambler, dealer));
    }

    public DisplayResult.Result play(Player better, Player dealer) {
        try {
            while (true) {
                showGameState(better, dealer);
                Choices choices = askHitOrStay();
                if (choices.equals(HIT)) {
                    better.deal();
                    continue;
                }
                while (dealer.value() < 17) {
                    dealer.deal();
                    showGameState(better, dealer);
                }
                return displayResult.result(better, dealer);
            }
        } catch (Player.PlayerBustException e) {
            showGameState(better, dealer);
            return displayResult.result(better, dealer);
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

    private void showGameState(Player better, Player dealer) {
        System.out.println("better's value is: " + better.value() + " with cards:" + better.cards);
        System.out.println("dealer's value is: " + dealer.value() + " with cards:" + dealer.cards);
        System.out.println("------------------------------------------");
    }

    private static class WrongInputException extends RuntimeException {
    }

    public enum Choices {
        HIT, STAY
    }
}
