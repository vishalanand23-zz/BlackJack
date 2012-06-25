package normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static normal.GamePlay.Choices.HIT;
import static normal.GamePlay.Choices.STAY;

public class GamePlay {

    public static void main(String[] args) {
        Player better = new Player();
        Player dealer = new Player();
        better.deal();
        better.deal();
        dealer.deal();
        System.out.println(new GamePlay().play(better, dealer));
    }

    public Result play(Player better, Player dealer) {
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
                return result(better, dealer);
            }
        } catch (Player.PlayerBustException e) {
            showGameState(better, dealer);
            return result(better, dealer);
        }
    }

    public Result result(Player better, Player dealer) {
        int playerSum = better.value();
        int dealerSum = dealer.value();
        if (playerSum == -1) return Result.DEALER_WIN;
        if (dealerSum == -1) return Result.PLAYER_WIN;
        if (playerSum > dealerSum) return Result.PLAYER_WIN;
        if (dealerSum > playerSum) return Result.DEALER_WIN;
        else return Result.PUSH;
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

    public enum Result {
        PLAYER_WIN, DEALER_WIN, PUSH, PLAYER_BLACK_JACK
    }

    public enum Choices {
        HIT, STAY
    }
}
