package sy.core;

import java.util.Random;

public abstract class Globals {
    public static final Random RANDOM = new Random();

    public static final float getRandomFloat(float min, float max) {
        return min + RANDOM.nextFloat() * (max - min);
    }
}
