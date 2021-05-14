package sy.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import sy.core.Visuals.AnimationController;
import sy.rendering.RenderPipeline;

public class Critter extends GameObject {
    private float frameTimer;
    private AnimationController animationController;
    Sprite sprite;

    public Critter(String uuid){
        super(uuid);
    }

    public void init(AnimationController animationController) {
        sprite = new Sprite(animationController.getCurrentFrame());
    }

    @Override
    public void update(float delta) {
        frameTimer += delta;
        if(frameTimer > 0.25f)
            sprite.setTexture(animationController.getNextFrame().getTexture());
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.add(sprite, 3);
    }
}
