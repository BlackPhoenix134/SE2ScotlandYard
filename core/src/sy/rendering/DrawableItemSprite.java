package sy.rendering;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public  class DrawableItemSprite extends DrawableItem {
    private Sprite sprite;

    public DrawableItemSprite() {

    }

    public DrawableItemSprite(Sprite sprite, int drawLayer, ShaderProgram shader) {
        super(drawLayer, shader);
        this.sprite = sprite;
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setPosition(sprite.getX() - sprite.getWidth()/2f, sprite.getY() - sprite.getHeight()/2f);
        sprite.draw(batch);
        sprite.setPosition(sprite.getX() + sprite.getWidth()/2f, sprite.getY() + sprite.getHeight()/2f);
    }
}
