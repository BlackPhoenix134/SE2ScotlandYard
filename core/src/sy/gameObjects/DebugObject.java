package sy.gameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import sy.rendering.RenderPipeline;

public class DebugObject extends GameObject {
    private Vector2 position = Vector2.Zero;

    DebugObject(String uuid) {
        super(uuid);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.drawCircle(position, 8, Color.RED, true, 10000);
    }
}
