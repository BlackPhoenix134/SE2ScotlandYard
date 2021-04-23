package sy.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

public class PrimitiveRenderer  implements Disposable {
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public void updateBatchMatrix(OrthographicCamera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
