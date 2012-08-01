package normal;

import org.junit.Test;

import static normal.DisplayResult.Result.GAMBLER_WIN;
import static normal.GetChoice.Choice.STAY;
import static normal.PlayerTest.DealerStub;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GamePlayTest {

    @Test
    public void gamblerBust() {
        GetChoice getChoice = mock(GetChoice.class);
        DisplayResult getResult = new DisplayResult();
        GamePlay gamePlay = new GamePlay(getChoice, getResult);
        when(getChoice.askHitOrStay()).thenReturn(STAY);
        DealerStub cardDealer = new DealerStub();
        cardDealer.setCards(new Card(7, 2), new Card(9, 2), new Card(11, 2), new Card(5, 2), new Card(7, 2));
        Player gambler = new Player(cardDealer);
        Player dealer = new Player(cardDealer);
        gambler.deal();
        gambler.deal();
        dealer.deal();
        gamePlay.play(gambler, dealer);
        verify(getChoice).askHitOrStay();
        assertEquals(GAMBLER_WIN, getResult.result(gambler, dealer));
    }
}
