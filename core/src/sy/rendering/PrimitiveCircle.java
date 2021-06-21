package sy.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import java.util.Arrays;
import java.util.Objects;

public class PrimitiveCircle extends Primitive {
    private int radius;
    private Color color;
    private boolean isFilled;

    public PrimitiveCircle(Pixmap pixmap, int radius, Color color, boolean isFilled) {
        super(pixmap);
        this.radius = radius;
        this.color = color;
        this.isFilled = isFilled;
    }

    public PrimitiveCircle(int radius, Color color, boolean isFilled) {
        this.radius = radius;
        this.color = color;
        this.isFilled = isFilled;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 31 * result + getClass().hashCode();
        result += 31 * result + radius;
        result += 31 * result + color.hashCode();
        result += 31 * result + (isFilled ? 1 : 0);
        return result;
    }
}
