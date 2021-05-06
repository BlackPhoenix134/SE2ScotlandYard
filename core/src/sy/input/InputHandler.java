package sy.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class InputHandler extends InputAdapter implements GestureDetector.GestureListener {
    private float currentScale = 1;
    private float zoomValue = 1;
    private Vector2 dragValue = new Vector2();

    private TouchDownListener touchDownListener;
    public void setTouchDownListener(TouchDownListener listener) {
        this.touchDownListener = listener;
    }

    public InputHandler(){
        setProcesses();
    }

    public void setProcesses(){
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(new GestureDetector(this));
        Gdx.input.setInputProcessor(multiplexer);
    }

    public float getZoomValue() {
        return zoomValue;
    }

    public Vector2 getDragValue(){
        return dragValue;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        currentScale = zoomValue;
        if(touchDownListener != null){
            touchDownListener.onTouchDown();
        }
        return super.touchDown(screenX,screenY,pointer,button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
        dragValue.x = deltaX;
        dragValue.y = deltaY;
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        float ratio = initialDistance / distance;
        this.zoomValue = currentScale * ratio;
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
