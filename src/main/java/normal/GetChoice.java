package normal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetChoice {
    public GetChoice() {
    }

    Choice askHitOrStay() {
        System.out.println("For stay Enter 'S'/'s'. For hit Enter 'H'/'h'.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = br.readLine().substring(0, 1);
            if (input.compareToIgnoreCase("S") == 0) return Choice.STAY;
            if (input.compareToIgnoreCase("H") == 0) return Choice.HIT;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new WrongInputException();
    }

    private static class WrongInputException extends RuntimeException {
    }

    public enum Choice {
        HIT, STAY
    }
}