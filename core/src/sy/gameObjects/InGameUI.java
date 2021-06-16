package sy.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.core.Clickable;
import sy.core.Gameplay;
import sy.core.Math.GoodMath;
import sy.core.Physics.Area2D;
import sy.core.Physics.BoundingBox;
import sy.core.clickHandling.ObjectClickHandler;
import sy.gameObjects.GameObject;
import sy.gameObjects.SpriteDrawableObject;
import sy.input.prio.InputEvent;
import sy.rendering.RenderPipeline;

public class InGameUI extends GameObject{
    private Gameplay gameplay;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private ObjectClickHandler objectClickHandler;

    InGameUI(String uuid) {
        super(uuid);
    }

    public void initialize(Gameplay gameplay, ObjectClickHandler objectClickHandler) {
        this.gameplay = gameplay;
        this.objectClickHandler = objectClickHandler;
        if(!(gameplay.getPlayerPawnObject() instanceof PawnMisterXObject))
            createMrXCardsUsedButton();
    }


    public void createMrXCardsUsedButton() {
        SpriteDrawableObject obj = gameObjectManager.create(SpriteDrawableObject.class);
        Sprite sprite = new Sprite(SYAssetManager.getAsset(AssetDescriptors.BIKE));
        sprite.setScale(GoodMath.ratio(sprite.getHeight() , Gdx.graphics.getHeight() / 5f));
        sprite.setPosition(
                //0 + (sprite.getWidth() * sprite.getScaleX()) / 2,
                //Gdx.graphics.getHeight() - (sprite.getHeight() * sprite.getScaleY()) / 2f);
               0, 0);
        obj.setSprite(sprite);
        obj.setDrawOrder(100);
        obj.setUiObject(true);
        objectClickHandler.addTouchUpClickable(new Clickable() {
            @Override
            public void onClicked(InputEvent inputEvent) {
                inputEvent.setConsumed(true);
            }

            @Override
            public Vector2 getPosition() {
                return new Vector2(sprite.getX(), sprite.getY());
            }

            @Override
            public Area2D getArea2D() {
                float scaledWidth = sprite.getWidth() * sprite.getScaleX();
                float scaledHeight = sprite.getHeight() * sprite.getScaleY();
                Gdx.app.log("SEXY", "Area2D center " + getPosition().x + " " + getPosition().y);
                return new BoundingBox(getPosition().x, getPosition().y, scaledWidth, scaledHeight);
            }
        }, 500, true);
    }

    @Override
    public void update(float delta) {
        gameObjectManager.update(delta);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
    }
}
