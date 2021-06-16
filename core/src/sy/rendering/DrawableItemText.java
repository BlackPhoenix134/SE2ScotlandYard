package sy.rendering;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;

public class DrawableItemText extends DrawableItem {
    private BitmapFont font = new BitmapFont(); //ToDo: monitor for garbage here
    private String text;
    private Vector2 position;
    private float scale;

    public DrawableItemText(String text, Vector2 position, float scale, int drawLayer, ShaderProgram shader) {
        super(drawLayer, shader);
        this.text = text;
        this.position = position;
        this.scale = scale;
    }

    @Override
    public void render(SpriteBatch batch) {
        font.getData().scale(scale);
        font.draw(batch, text, position.x, position.y);
    }
}
