package sy.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import sy.rendering.RenderPipeline;

public class DebugObject extends GameObject {
    private Vector2 position = Vector2.Zero;
    private Texture texture;

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public DebugObject(String uuid) {
        super(uuid);
    }


    private float bong = 1;
    @Override
    public void update(float delta) {
        position.x += 10 * delta;
        if(position.x > 450)
            bong = -1;
        if(position.x < 10)
            bong = 1;
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.add(texture, position);
    }
}
