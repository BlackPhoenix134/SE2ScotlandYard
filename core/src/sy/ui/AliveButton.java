package sy.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class AliveButton extends Button {

    private final int originalHeight;
    private final int originalWidth;
    private Array<AliveButtonListener> listeners = new Array<>();

    public AliveButton(Texture texture) {
        originalWidth = texture.getWidth();
        originalHeight = texture.getHeight();
        Drawable drawable = new TextureRegionDrawable(texture);
        setStyle(new ButtonStyle(drawable, drawable, drawable));
        setSize(getPrefWidth(), getPrefHeight());
        setTransform(true);

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clearActions();
                addAction(Actions.scaleTo(1.25f,1.25f,0.2f));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                addAction(Actions.sequence(
                        Actions.scaleTo(1f,1f,0.2f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                for(AliveButtonListener listener: listeners) {
                                    try {
                                        listener.onClick();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        })
                ));
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        setOrigin(Align.center);
    }

    public void addListener(AliveButtonListener listener) {
        listeners.add(listener);
    }

    public void removeListener(AliveButtonListener listener){
        listeners.removeValue(listener, false);
    }

    public void fillX(float width){
        float per = width/originalWidth;
        setSize(originalWidth * per, originalHeight * per);
    }

    public void fillY(float height){
        float per = height/originalHeight;
        setSize(originalWidth * per, originalHeight * per);
    }

    public interface AliveButtonListener {
        void onClick() throws Exception;
    }
}
