package sy.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class FillableImage extends Image {
    private final int originalWidth;
    private final int originalHeight;

    public FillableImage(Texture texture) {
        super(texture);
        originalWidth = texture.getWidth();
        originalHeight = texture.getHeight();
    }

    public void fillX(float width){
        float per = width/originalWidth;
        setSize(originalWidth * per, originalHeight * per);
    }

    public void fillY(float height){
        float per = height/originalHeight;
        setSize(originalWidth * per, originalHeight * per);
    }
}
