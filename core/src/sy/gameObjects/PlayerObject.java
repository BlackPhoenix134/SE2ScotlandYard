package sy.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.rendering.RenderPipeline;

public class PlayerObject extends GameObject {

    private int playerID;
    private Vector2 position;
    private Sprite sprite;


    public PlayerObject(String uuid) {
        super(uuid);
        position = Vector2.Zero;
        Texture texture = SYAssetManager.getAsset(AssetDescriptors.MONSTER1);
        sprite = new Sprite(texture);
        sprite.setScale(0.45f);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        sprite.setPosition(position.x, position.y);
        pipeline.add(sprite, 55);
    }

    public int getPlayerID() {
        return playerID;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
