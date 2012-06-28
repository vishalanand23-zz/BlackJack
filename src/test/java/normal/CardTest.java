package normal;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CardTest {

    @Test
    public void cardValueLessThanTen() {
        Card card = new Card(2, 2);
        int num = card.value();
        assertEquals(2, num);
    }

    @Test
    public void cardValueMoreThanTen() {
        Card card = new Card(12, 2);
        int num = card.value();
        assertEquals(10, num);
    }
}
