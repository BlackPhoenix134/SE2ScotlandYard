package sy.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
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
    private DefaultRenderer worldRenderer;
    private DefaultRenderer uiRenderer;
    private PrimitiveRenderer primitiveRenderer;
    private ShaderManager shaderManager;

    private OrthographicCamera camera;
    private Viewport viewport;

    public RenderPipeline(ShaderManager shaderManager, OrthographicCamera camera, ExtendViewport viewport) {
        this.shaderManager = shaderManager;
        this.camera = camera;
        this.viewport = viewport;
        this.worldRenderer = new DefaultRenderer(new SpriteBatch());
        this.uiRenderer = new DefaultRenderer(new SpriteBatch());
        this.primitiveRenderer = new PrimitiveRenderer(worldRenderer);
    }

    public void begin() {
        worldRenderer.init();
        uiRenderer.init();
    }

    public void end() {
        worldRenderer.render();
        uiRenderer.render();
    }


    public void add(Texture img, Vector2 position, int drawLayer) {
        Vector2 centeredPos = new Vector2(position.x - img.getWidth() / 2f, position.y - img.getHeight() / 2f);
        worldRenderer.add(new DrawableItemTexture(img, centeredPos, drawLayer, ShaderManager.defaultShader));
    }

    public void add(Sprite sprite, int drawLayer) {
        add(sprite,  drawLayer, ShaderManager.defaultShader);
    }

    public void add(Sprite sprite, int drawLayer, ShaderProgram shader) {
        worldRenderer.add(new DrawableItemSprite(sprite,  drawLayer, shader));
    }

    public void add(String text, Vector2 position, int drawLayer) {
        add(text, position, 1, drawLayer);
    }

    public void add(String text, Vector2 position, float scale, int drawLayer) {
        worldRenderer.add(new DrawableItemText(text, position, scale, drawLayer, ShaderManager.defaultShader));
    }

    public void addUi(Texture img, Vector2 position, int drawLayer) {
        Vector2 centeredPos = new Vector2(position.x - img.getWidth() / 2f, position.y - img.getHeight() / 2f);
        uiRenderer.add(new DrawableItemTexture(img, centeredPos, drawLayer, ShaderManager.defaultShader));
    }

    public void addUi(Sprite sprite, int drawLayer) {
        addUi(sprite,  drawLayer, ShaderManager.defaultShader);
    }

    public void addUi(Sprite sprite, int drawLayer, ShaderProgram shader) {
        uiRenderer.add(new DrawableItemSprite(sprite,  drawLayer, shader));
    }

    public void addUi(String text, Vector2 position, int drawLayer) {
        addUi(text, position, 1, drawLayer);
    }

    public void addUi(String text, Vector2 position, float scale, int drawLayer) {
        uiRenderer.add(new DrawableItemText(text, position, scale, drawLayer, ShaderManager.defaultShader));
    }

    public void drawCircle(Vector2 position, int radius, Color color, boolean isFilled, int drawLayer) {
        PrimitiveCircle primitive = new PrimitiveCircle(radius, color, isFilled);
        if(primitiveRenderer.isInCache(primitive)) {
            primitive = primitiveRenderer.getCachedPrimitive(primitive.hashCode());
        } else {
            primitive.setTexture(primitiveRenderer.createPixmapCircle(radius, color, isFilled));
            primitiveRenderer.toCache(primitive);
        }
       add(primitive.getTexture(), position, drawLayer);
    }

    public void updateBatchMatrix() {
        worldRenderer.updateBatchMatrix(camera);
        primitiveRenderer.updateBatchMatrix(camera);
    }

    @Override
    public void dispose(){
        worldRenderer.dispose();
        primitiveRenderer.dispose();
    }
}
