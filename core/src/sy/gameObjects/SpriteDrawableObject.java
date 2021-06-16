package sy.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sy.core.Clickable;
import sy.core.Physics.Area2D;
import sy.input.prio.InputEvent;
import sy.rendering.RenderPipeline;

public class SpriteDrawableObject extends GameObject{
    private Sprite sprite;
    private int drawOrder;
    private boolean isUiObject;

    SpriteDrawableObject(String uuid) {
        super(uuid);
    }

    public SpriteDrawableObject() {
        this(null);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setDrawOrder(int drawOrder) {
        this.drawOrder = drawOrder;
    }

    public void setUiObject(boolean uiObject) {
        isUiObject = uiObject;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getDrawOrder() {
        return drawOrder;
    }

    public boolean isUiObject() {
        return isUiObject;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        if(isUiObject)
            pipeline.addUi(sprite, drawOrder);
        else
            pipeline.add(sprite, drawOrder);
    }
}
