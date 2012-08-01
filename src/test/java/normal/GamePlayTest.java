package normal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GamePlayTest {

    @Test
    public void gamblerBust() {
        GetChoice getChoice = mock(GetChoice.class);
        DisplayResult displayResult = new DisplayResult();
        GamePlay gamePlay = new GamePlay(getChoice, displayResult);
        when(getChoice.askHitOrStay()).thenReturn(GetChoice.Choice.STAY);
        PlayerTest.DealerStub cardDealer = new PlayerTest.DealerStub();
        cardDealer.setNumber(7);
        cardDealer.setSuit(2);
        Player gambler = new Player(cardDealer);
        Player dealer = new Player(cardDealer);
        gambler.deal();
        gambler.deal();
        dealer.deal();
        gamePlay.play(gambler, dealer);
        assertEquals(DisplayResult.Result.DEALER_WIN, displayResult.result(gambler, dealer));
    }
}
