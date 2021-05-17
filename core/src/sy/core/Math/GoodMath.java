package sy.core.Math;

import com.badlogic.gdx.math.Vector2;

public abstract class GoodMath {
    public static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    public static Vector2 sub(Vector2 a, Vector2 b) {
        return new Vector2(a.x - b.x, a.y - b.y);
    }
    public static Vector2 mul(Vector2 a, Vector2 b) {
        return new Vector2(a.x * b.x, a.y * b.y);
    }
    public static Vector2 div(Vector2 a, Vector2 b) {
        return new Vector2(a.x / b.x, a.y / b.y);
    }

    public static Vector2 add(Vector2 a, float value) {
        return new Vector2(a.x +value, a.y + value);
    }

    public static Vector2 sub(Vector2 a, float value) {
        return new Vector2(a.x - value, a.y -value);
    }
    public static Vector2 mul(Vector2 a, float value) {
        return new Vector2(a.x * value, a.y * value);
    }
    public static Vector2 div(Vector2 a, float value) {
        return new Vector2(a.x / value, a.y / value);
    }

    public static boolean equals(Vector2 a, Vector2 b, float theta) {
        return equals(a.x, b.x, theta) && equals(a.y, b.y, theta);
    }

    public static boolean equals(float a, float b, float theta) {
        return Math.abs(a - b) <= theta;
    }

    public static Vector2 moveTowards(Vector2 from, Vector2 to, float maxDistanceDelta) {
        // avoid vector ops because current scripting backends are terrible at inlining
        float toVectorX = to.x - from.x;
        float toVectorY = to.y - from.y;

        float sqdist = toVectorX * toVectorX + toVectorY * toVectorY;

        if (sqdist == 0 || (maxDistanceDelta >= 0 && sqdist <= maxDistanceDelta * maxDistanceDelta))
            return to;
        float dist = (float)Math.sqrt(sqdist);
        return new Vector2(from.x + toVectorX / dist * maxDistanceDelta,
                from.y + toVectorY / dist * maxDistanceDelta);
    }
}
