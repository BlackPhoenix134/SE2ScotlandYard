package sy.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

public class PrimitiveRenderer  implements Disposable {
    private DefaultRenderer defaultRenderer;
    private Map<Integer, Primitive> primtiveCache = new HashMap<>();

    public PrimitiveRenderer(DefaultRenderer defaultRenderer) {
        this.defaultRenderer = defaultRenderer;
    }

    public void drawCircle(Vector2 position, int radius, Color color, boolean isFilled) {
        PrimitiveCircle primitive = new PrimitiveCircle(radius, color, isFilled);
        if(primtiveCache.containsKey(primitive.hashCode())) {
            primitive = (PrimitiveCircle)primtiveCache.get(primitive.hashCode());
        } else {
            primitive.setTexture(createPixmapCircle(radius, color, isFilled));
            primtiveCache.put(primitive.hashCode(), primitive);
        }
        defaultRenderer.add(primitive.getTexture(), position);
    }

    public static Pixmap createPixmapCircle(int radius, Color color, boolean isFilled) {
        Pixmap pixmap=new Pixmap(2*radius+1, 2*radius+1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        if(isFilled)
            pixmap.fillCircle(radius, radius, radius);
        else
            pixmap.drawCircle(radius, radius, radius);
        pixmap.drawLine(radius, radius, 2*radius, radius);
        return pixmap;
    }

    public static Pixmap createPixmapLine(int radius, Color color, boolean isFilled) {
        Pixmap pixmap=new Pixmap(2*radius+1, 2*radius+1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
       
        pixmap.drawLine(radius, radius, 2*radius, radius);
        return pixmap;
    }

    public void updateBatchMatrix(OrthographicCamera camera) {
    }


    @Override
    public void dispose() {
    }
}
