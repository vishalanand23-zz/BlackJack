package normal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DisplayResult {

    private Dao dao = new Dao();

    public Result result(Player better, Player dealer) {
        int playerSum = better.value();
        int dealerSum = dealer.value();
        Result gameResult;
        if (playerSum == -1) gameResult = Result.DEALER_WIN;
        else if (dealerSum == -1) gameResult = Result.PLAYER_WIN;
        else if (playerSum > dealerSum) gameResult = Result.PLAYER_WIN;
        else if (dealerSum > playerSum) gameResult = Result.DEALER_WIN;
        else gameResult = Result.PUSH;
        dao.insert(gameResult);
        return gameResult;
    }

    public enum Result {
        PLAYER_WIN, DEALER_WIN, PUSH
    }

    public static class Dao {
        private Connection con;
        private PreparedStatement st;

        public Dao() {
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/blackjack", "root", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void insert(Result gameResult) {
            try {
                st = con.prepareStatement("insert into result_summary(result) values(?)");
                st.setString(1, gameResult.toString());
                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}