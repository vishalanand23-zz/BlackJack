package normal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DisplayResultTest {

    private Player gambler;
    private Player dealer;
    private PlayerTest.DealerStub cardsDealer;
    private DisplayResult displayResult = new DisplayResult();


    @Before
    public void setUp() {
        cardsDealer = new PlayerTest.DealerStub();
        gambler = new Player(cardsDealer);
        dealer = new Player(cardsDealer);
    }

    @Test
    public void gamblerShouldWin() {
        dealCardsToPlayer(gambler, cardsDealer, 9, 2);
        dealCardsToPlayer(gambler, cardsDealer, 8, 1);
        dealCardsToPlayer(gambler, cardsDealer, 3, 3);
        dealCardsToPlayer(dealer, cardsDealer, 1, 3);
        dealCardsToPlayer(dealer, cardsDealer, 8, 3);
        assertEquals(DisplayResult.Result.GAMBLER_WIN, displayResult.result(gambler, dealer));
    }

    @Test
    public void dealerShouldWin() {
        dealCardsToPlayer(gambler, cardsDealer, 9, 2);
        dealCardsToPlayer(gambler, cardsDealer, 8, 1);
        dealCardsToPlayer(dealer, cardsDealer, 1, 3);
        dealCardsToPlayer(dealer, cardsDealer, 8, 3);
        assertEquals(DisplayResult.Result.DEALER_WIN, displayResult.result(gambler, dealer));
    }

    @Test
    public void dealerBustGamblerShouldWin() {
        dealCardsToPlayer(gambler, cardsDealer, 9, 2);
        dealCardsToPlayer(dealer, cardsDealer, 12, 3);
        dealCardsToPlayer(dealer, cardsDealer, 11, 3);
        dealCardsToPlayer(dealer, cardsDealer, 8, 3);
        assertEquals(DisplayResult.Result.GAMBLER_WIN, displayResult.result(gambler, dealer));
    }

    @Test
    public void gamblerBustDealerShouldWin() {
        dealCardsToPlayer(gambler, cardsDealer, 9, 2);
        dealCardsToPlayer(gambler, cardsDealer, 8, 1);
        dealCardsToPlayer(gambler, cardsDealer, 5, 1);
        dealCardsToPlayer(dealer, cardsDealer, 12, 3);
        dealCardsToPlayer(dealer, cardsDealer, 11, 3);
        dealCardsToPlayer(dealer, cardsDealer, 8, 3);
        assertEquals(DisplayResult.Result.DEALER_WIN, displayResult.result(gambler, dealer));
    }

    @Test
    public void push() {
        dealCardsToPlayer(gambler, cardsDealer, 9, 2);
        dealCardsToPlayer(gambler, cardsDealer, 8, 1);
        dealCardsToPlayer(dealer, cardsDealer, 12, 3);
        dealCardsToPlayer(dealer, cardsDealer, 4, 3);
        dealCardsToPlayer(dealer, cardsDealer, 3, 3);
        assertEquals(DisplayResult.Result.PUSH, displayResult.result(gambler, dealer));
    }

    private void dealCardsToPlayer(Player p, PlayerTest.DealerStub dealer, int number, int suit) {
        dealer.setCards(new Card(number, suit));
        p.deal();
    }
}
