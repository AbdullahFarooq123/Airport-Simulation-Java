import java.util.Random;

public class Static {
    public static int getRandomInt(int max, int min) {
        return new Random().nextInt(max-min) + min;
    }
    public static long getRandomLong(int max, int min) {
        return new Random().nextLong() % max + min;
    }
    public static boolean getRandomBoolean(){
        return new Random().nextBoolean();
    }
}
