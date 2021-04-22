package sy.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import sy.gameObjects.GameObject;

public class RenderPipeline implements Disposable {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public RenderPipeline(SpriteBatch batch, OrthographicCamera camera, ExtendViewport viewport) {
        this.camera = camera;
        this.viewport = viewport;
        this.batch = batch;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void begin() {
        batch.begin();
    }

    //ToDo: drawing has to take rotation/scale into account
    public void add(Texture img, Vector2 position) {
        batch.draw(img, position.x, position.y);
    }

    public void end() {
        batch.end();
    }

    public void updateBatchMatrix() {
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }
}
