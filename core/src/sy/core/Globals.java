package sy.core;

import java.util.Random;

public abstract class Globals {
    public static final Random RANDOM = new Random();

    public static float getRandomFloat(float min, float max) {
        return min + RANDOM.nextFloat() * (max - min);
    }

    public static int getRandomInt(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }
}
