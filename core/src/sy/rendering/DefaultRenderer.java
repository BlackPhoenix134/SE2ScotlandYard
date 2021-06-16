package sy.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sy.assets.ShaderManager;

public class DefaultRenderer implements Disposable {
    private SpriteBatch batch;
    private DrawableItemComparator drawableItemComparator = new DrawableItemComparator();
    private List<DrawableItem> drawables = new ArrayList<>();

    DefaultRenderer(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    void init() {
        drawables.clear();
    }


    void begin() {
        batch.begin();
    }

    public void add(DrawableItem drawableItem) {
        drawables.add(drawableItem);
    }

    void end() {
        batch.end();
    }

    void updateBatchMatrix(OrthographicCamera camera) {
        batch.setProjectionMatrix(camera.combined);
    }

    public void setShader(ShaderProgram shader) {
        if(batch.isDrawing()) {
            this.end();
            batch.setShader(shader);
            this.begin();
        } else {
            batch.setShader(shader);
        }
    }

    public void render() {
        Collections.sort(drawables, drawableItemComparator);
        ShaderProgram lastShader = ShaderManager.defaultShader;
        setShader(ShaderManager.defaultShader);
        begin();

        for(DrawableItem drawable : drawables) {
            if(isNewShader(drawable.getShader(), lastShader)) {
                setShader(drawable.getShader());
                lastShader = drawable.getShader();
            }

            drawable.render(batch);
        }
        end();
    }

    private boolean isNewShader(ShaderProgram shader1, ShaderProgram shader2) {
        return shader1 != shader2;
    }

    private class DrawableItemComparator implements Comparator<DrawableItem> {
        @Override
        public int compare(DrawableItem d1, DrawableItem d2) {
            return Integer.compare(d1.getDrawLayer(), d2.getDrawLayer());
        }
    }
}
