package sy.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.rendering.RenderPipeline;

public class PlayerObject extends GameObject {

    private int playerID;
    private Vector2 position;
    private Texture texture;


    public PlayerObject(String uuid) {
        super(uuid);
        position = Vector2.Zero;
        texture = SYAssetManager.getAssetManager().get(AssetDescriptors.MONSTER1);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.add(texture, position, 55);
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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

}
