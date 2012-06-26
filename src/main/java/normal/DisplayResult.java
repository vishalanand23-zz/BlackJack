package normal;

public class DisplayResult {
    public Result result(Player better, Player dealer) {
        int playerSum = better.value();
        int dealerSum = dealer.value();
        if (playerSum == -1) return Result.DEALER_WIN;
        if (dealerSum == -1) return Result.PLAYER_WIN;
        if (playerSum > dealerSum) return Result.PLAYER_WIN;
        if (dealerSum > playerSum) return Result.DEALER_WIN;
        else return Result.PUSH;
    }

    public enum Result {
        PLAYER_WIN, DEALER_WIN, PUSH
    }
}