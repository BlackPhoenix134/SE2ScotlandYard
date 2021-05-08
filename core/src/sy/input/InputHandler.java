package sy.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class InputHandler extends InputAdapter implements GestureDetector.GestureListener {

    private TouchUpListener touchUpListener;

    public void setTouchUpListener(TouchUpListener touchUpListener) {
        this.touchUpListener = touchUpListener;
    }

    private TouchDownListener touchDownListener;

    public void setTouchDownListener(TouchDownListener touchDownListener) {
        this.touchDownListener = touchDownListener;
    }

    private ZoomListener zoomListener;

    public void setZoomListener(ZoomListener zoomListener) {
        this.zoomListener = zoomListener;
    }

    private PanListener panListener;

    public void setPanListener(PanListener panListener) {
        this.panListener = panListener;
    }

    public InputHandler() {
        //Set up the handler
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(new GestureDetector(this));
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (touchDownListener != null) {
            touchDownListener.onTouchDown(screenX, screenY, pointer, button);
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (touchUpListener != null) {
            touchUpListener.onTouchUp(screenX, screenY, pointer, button);
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (panListener != null) {
            panListener.onPan(x, y, deltaX, deltaY);
        }
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        if (zoomListener != null) {
            zoomListener.onZoom(initialDistance, distance);
        }
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    public void removeAllEvents() {
        setTouchUpListener(null);
        setTouchDownListener(null);
        setZoomListener(null);
        setPanListener(null);
    }
}
