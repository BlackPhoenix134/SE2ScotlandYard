package sy.core.Math;

import java.util.Random;

public abstract class Randum {
    private static Random random = new Random();
    public static Random get() {
        return random;
    }
}
