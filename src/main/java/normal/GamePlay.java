package normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GamePlay {
    Helper helper = new Helper();

    public static void main(String[] args) {
        Card[] player = new Card[0];
        Card[] dealer = new Card[0];
        new GamePlay().initializeGame(player, dealer);
    }

    private void initializeGame(Card[] player, Card[] dealer) {
        player = add(player, helper.deal());
        player = add(player, helper.deal());
        dealer = add(dealer, helper.deal());
        System.out.println(playGame(player, dealer));
    }

    public Helper.Result playGame(Card[] player, Card[] dealer) {
        showGameState(player, dealer);
        while (true) {
            showGameState(player, dealer);
            if (helper.isBust(helper.value(player))) {
                return Helper.Result.DEALER_WIN;
            } else {
                Helper.Choices choice = offerChoices();
                if (choice == Helper.Choices.STAY) {
                    while (helper.value(dealer) < 17) {
                        dealer = add(dealer, helper.deal());
                        showGameState(player, dealer);
                        if (helper.isBust(helper.value(dealer))) {
                            return Helper.Result.PLAYER_WIN;
                        }
                    }
                    return helper.result(helper.value(player), helper.value(dealer));
                } else {
                    player = add((player), helper.deal());
                }
            }
        }
    }

    private Helper.Choices offerChoices() {
        System.out.println("For stay Enter 'S'/'s'. For hit Enter 'H'/'h'.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = br.readLine().substring(0, 1);
            if (input.compareToIgnoreCase("S") == 0) return Helper.Choices.STAY;
            if (input.compareToIgnoreCase("H") == 0) return Helper.Choices.HIT;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new WrongInputException();
    }

    private void showGameState(Card[] player, Card[] dealer) {
        System.out.println("player's value is: " + helper.value(player));
        System.out.println("dealer's value is: " + helper.value(dealer));
        System.out.println("------------------------------------------");
    }

    private Card[] add(Card[] player, Card card) {
        int length = player.length;
        Card[] newPlayer = new Card[length + 1];
        System.arraycopy(player, 0, newPlayer, 0, player.length);
        newPlayer[length] = card;
        return newPlayer;
    }

    private static class WrongInputException extends RuntimeException {
    }
}
