package normal;

public class RandomNumberGenerator {
    public int generateInRange(int low, int high) {
        return (int) (Math.ceil(Math.random() * (high - low + 1)) + low - 1);
    }
}
