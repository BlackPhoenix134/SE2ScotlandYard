package sy.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import sy.assets.ShaderManager;
import sy.rendering.RenderPipeline;

public class ShaderDebugObject extends GameObject {
    private Sprite sprite;
    private ShaderProgram shader;

    ShaderDebugObject(String uuid) {
        super(uuid);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public ShaderProgram getShader() {
        return shader;
    }

    public void setShader(ShaderProgram shader) {
        this.shader = shader;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.add(sprite, 100000, shader);
    }
}
