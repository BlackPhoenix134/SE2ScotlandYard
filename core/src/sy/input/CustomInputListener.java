package sy.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class CustomInputListener extends InputAdapter {
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("GAME", "You touched Pos X: "+screenX+", Y: "+screenY);
        return super.touchDown(screenX,screenY,pointer,button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("GAME", "You left the screen at Pos X: "+screenX+", Y: "+screenY);
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Gdx.app.log("GAME", "You dragged to Pos X: "+screenX+", Y: "+screenY);
        return super.touchDragged(screenX, screenY, pointer);
    }

}
