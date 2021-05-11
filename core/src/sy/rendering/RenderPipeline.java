package sy.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sy.assets.ShaderManager;

public class RenderPipeline implements Disposable {
    private DefaultRenderer defaultRenderer;
    private PrimitiveRenderer primitiveRenderer;
    private SpriteBatch batch;
    private ShaderManager shaderManager;

    private OrthographicCamera camera;
    private Viewport viewport;
    private DrawableItemComparator drawableItemComparator = new DrawableItemComparator();
    private List<DrawableItem> drawables = new ArrayList<DrawableItem>();

    public RenderPipeline(SpriteBatch batch, ShaderManager shaderManager, OrthographicCamera camera, ExtendViewport viewport) {
        this.batch = batch;
        this.shaderManager = shaderManager;
        this.camera = camera;
        this.viewport = viewport;
        this.defaultRenderer = new DefaultRenderer(batch);
        this.primitiveRenderer = new PrimitiveRenderer(defaultRenderer);
    }

    public void begin() {
        drawables.clear();
    }

    public void end() {
        renderDrawables();
    }

    public DefaultRenderer getDefaultRenderer() {
        return defaultRenderer;
    }

    public void add(Texture img, Vector2 position, int drawLayer) {
        drawables.add(new DrawableItem(img, position, drawLayer));
    }

    public void add(Sprite sprite, int drawLayer) {
        drawables.add(new DrawableItem(sprite, drawLayer));
    }

    private void renderDrawables() {
        Collections.sort(drawables, drawableItemComparator);

        defaultRenderer.begin();
       // batch.setShader(shaderManager.loadDefaultShader());
        for(DrawableItem drawable : drawables) {
            if(drawable.getSprite() != null)
                defaultRenderer.add(drawable.getSprite());
            else
                defaultRenderer.add(drawable.getTexture(), drawable.getPosition());
        }

        defaultRenderer.end();
    }

    public void drawCircle(Vector2 position, int radius, Color color, boolean isFilled, int drawLayer) {
        PrimitiveCircle primitive = new PrimitiveCircle(radius, color, isFilled);
        if(primitiveRenderer.isInCache(primitive)) {
            primitive = (PrimitiveCircle)primitiveRenderer.getCachedPrimitive(primitive.hashCode());
        } else {
            primitive.setTexture(primitiveRenderer.createPixmapCircle(radius, color, isFilled));
            primitiveRenderer.toCache(primitive);
        }
       add(primitive.getTexture(), position, drawLayer);
    }


    public PrimitiveRenderer getPrimitiveRenderer() {
        return primitiveRenderer;
    }




    public void updateBatchMatrix() {
        defaultRenderer.updateBatchMatrix(camera);
        primitiveRenderer.updateBatchMatrix(camera);
    }

    @Override
    public void dispose(){
        defaultRenderer.dispose();
        primitiveRenderer.dispose();
    }

    private class DrawableItemComparator implements Comparator<DrawableItem> {

        @Override
        public int compare(DrawableItem d1, DrawableItem d2) {
            return d1.getDrawLayer() - d2.getDrawLayer();
        }
    }
}
