package sy.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public abstract class DrawableItem {
    protected int drawLayer;
    protected ShaderProgram shader;

    public DrawableItem(int drawLayer, ShaderProgram shader) {
        this.drawLayer = drawLayer;
        this.shader = shader;
    }

    public int getDrawLayer() {
        return drawLayer;
    }

    public ShaderProgram getShader() {
        return shader;
    }

    public void setShader(ShaderProgram shader) {
        this.shader = shader;
    }

    public void setDrawLayer(int drawLayer) {
        this.drawLayer = drawLayer;
    }

    public abstract void render(SpriteBatch batch);
}
