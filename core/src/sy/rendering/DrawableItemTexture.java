package sy.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;

public class DrawableItemTexture extends DrawableItem {
    private Texture texture;
    private Vector2 position;

    public DrawableItemTexture() {

    }


    public DrawableItemTexture(Texture texture, Vector2 position, int drawLayer, ShaderProgram shader) {
        super(drawLayer, shader);
        this.texture = texture;
        this.position = position;
    }


    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }
}
