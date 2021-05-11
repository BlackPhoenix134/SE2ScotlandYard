package sy.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.Comparator;

public class DrawableItem {
    private Texture texture;
    private Vector2 position;
    private Sprite sprite;
    private int drawLayer;


    public DrawableItem(Texture texture, Vector2 position, int drawLayer) {
        this.texture = texture;
        this.position = position;
        this.drawLayer = drawLayer;
    }

    public DrawableItem(Sprite sprite, int drawLayer) {
        this.sprite = sprite;
        this.drawLayer = drawLayer;
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getDrawLayer() {
        return drawLayer;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setDrawLayer(int drawLayer) {
        this.drawLayer = drawLayer;
    }
}
