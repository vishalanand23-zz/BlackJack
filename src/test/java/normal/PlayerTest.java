package normal;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class PlayerTest {

    @Test
    public void testIsBustForLessThan21() {
        DealerStub dealer = new DealerStub();
        Player p = new Player(dealer);
        dealCardsToPlayer(p, dealer, 7, 2);
        dealCardsToPlayer(p, dealer, 12, 2);
        p.checkBust();
    }

    @Test
    public void testIsBustForMoreThan21() {
        try {
            DealerStub dealer = new DealerStub();
            Player p = new Player(dealer);
            dealCardsToPlayer(p, dealer, 7, 2);
            dealCardsToPlayer(p, dealer, 12, 2);
            dealCardsToPlayer(p, dealer, 10, 2);
            p.checkBust();
            fail();
        } catch (Player.PlayerBustException exception) {
        }
    }

    @Test
    public void testDeal() {
        DealerStub dealer = new DealerStub();
        Player p = new Player(dealer);
        dealCardsToPlayer(p, dealer, 7, 2);
        assertTrue(p.cards.contains(new Card(7, 2)));
    }

    @Test
    public void valueTest() {
        DealerStub dealer = new DealerStub();
        Player p = new Player(dealer);
        dealCardsToPlayer(p, dealer, 5, 2);
        assertTrue(p.value() == 5);
        dealCardsToPlayer(p, dealer, 1, 2);
        assertTrue(p.value() == 16);
        dealCardsToPlayer(p, dealer, 2, 2);
        assertTrue(p.value() == 18);
        dealCardsToPlayer(p, dealer, 1, 2);
        assertTrue(p.value() == 19);
        dealCardsToPlayer(p, dealer, 10, 2);
        assertTrue(p.value() == 19);
        dealCardsToPlayer(p, dealer, 12, 2);
        assertTrue(p.value() == -1);
    }

    private void dealCardsToPlayer(Player p, DealerStub dealer, int number, int suit) {
        dealer.setNumber(number);
        dealer.setSuit(suit);
        p.deal();
    }

    public static class DealerStub extends Dealer {

        private int number;
        private int suit;


        @Override
        public Card deal() {
            return new Card(number, suit);
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public void setSuit(int suit) {
            this.suit = suit;
        }
    }
}
