package normal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DealerTest {

    @Test
    public void testDeal() {
        Dealer d = new Dealer(new RandomNumberGeneratorStub());
        assertEquals(d.deal(), new Card(1, 1));
    }

    public static class RandomNumberGeneratorStub extends RandomNumberGenerator {
        @Override
        public int generateInRange(int low, int high) {
            return low;
        }
    }
}
