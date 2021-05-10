package sy.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import sy.rendering.RenderPipeline;

public class GameBoardObject extends GameObject {
    private Vector2 position = Vector2.Zero;
    private Texture texture;

    GameBoardObject(String uuid) {
        super(uuid);
    }


    public Vector2 getPosition() {
        return position;
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

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.add(texture, position, 2);
    }
}
