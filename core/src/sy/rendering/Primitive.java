package sy.rendering;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Primitive {
    private Texture texture;

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setTexture(Pixmap pixmap) {
        this.texture = new Texture(pixmap);
    }

    public Primitive(Pixmap pixmap) {
        texture = new Texture(pixmap);
    }
    public Primitive() {

    }
}
