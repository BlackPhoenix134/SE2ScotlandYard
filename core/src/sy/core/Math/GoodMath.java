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
}
