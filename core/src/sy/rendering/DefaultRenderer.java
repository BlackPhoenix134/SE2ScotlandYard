package sy.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import sy.gameObjects.GameObject;

public class DefaultRenderer implements Disposable {
    private SpriteBatch batch;

    DefaultRenderer(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    void begin() {
        batch.begin();
    }

    //ToDo: drawing has to take rotation/scale into account
    void add(Texture img, Vector2 position) {
        batch.draw(img, position.x, position.y);
    }

    void add(Sprite sprite) {
        sprite.setPosition(sprite.getX() - sprite.getWidth()/2f, sprite.getY() - sprite.getHeight()/2f);
        sprite.draw(batch);
        sprite.setPosition(sprite.getX() + sprite.getWidth()/2f, sprite.getY() + sprite.getHeight()/2f);
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
}
