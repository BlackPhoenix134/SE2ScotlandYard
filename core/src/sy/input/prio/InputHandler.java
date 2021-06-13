package sy.input.prio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sy.core.Consumer;


public class InputHandler extends InputAdapter implements GestureDetector.GestureListener {
    private List<InputEventListener> touchUpListeners = new ArrayList<>();
    private List<InputEventListener> touchDownListeners = new ArrayList<>();
    private List<InputEventListener> zoomListeners = new ArrayList<>();
    private List<InputEventListener> panListeners = new ArrayList<>();
    private InputEventListenerComparator inputEventListenerComparator = new InputEventListenerComparator();
    private boolean ignoreTouchUp = false;
    private InputMultiplexer inputMultiplexer;

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    public void addListener(InputEventType eventType, int priority, Consumer<InputEvent> callback) {
       InputEventListener listener = new InputEventListener(callback, priority);
        switch (eventType) {
            case TOUCH_UP:
                addListener(touchUpListeners, listener);
                break;
            case TOUCH_DOWN:
                addListener(touchDownListeners, listener);
                break;
            case ZOOM:
                addListener(zoomListeners, listener);
                break;
            case PAN:
                addListener(panListeners, listener);
                break;
        }
    }

    private void addListener(List<InputEventListener> listeners, InputEventListener inputEventListener) {
        listeners.add(inputEventListener);
        Collections.sort(listeners, inputEventListenerComparator);
    }

    private void invokeInputEvent(List<InputEventListener> listeners, InputEvent event) {
        int idx = 0;
        while (idx < listeners.size() && !event.isConsumed()) {
            listeners.get(idx).invoke(event);
            idx++;
        }
    }

    public InputHandler() {
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(new GestureDetector(this));
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        invokeInputEvent(touchDownListeners, new InputEvent(screenX, screenY, pointer, button));
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!ignoreTouchUp && pointer < 1) {
            invokeInputEvent(touchUpListeners, new InputEvent(screenX, screenY, pointer, button));
        }
        ignoreTouchUp = false;
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
        invokeInputEvent(panListeners, new InputEvent(x, y, deltaX, deltaY));
        ignoreTouchUp = true;
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        if (zoomListeners != null) {
            invokeInputEvent(zoomListeners, new InputEvent(initialDistance, distance, 0, 0));
            ignoreTouchUp = true;
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

    public void unsubscribeAll() {
        touchUpListeners.clear();
        touchDownListeners.clear();
        zoomListeners.clear();
        panListeners.clear();
    }



    private static class InputEventListenerComparator implements Comparator<InputEventListener>  {
        @Override
        public int compare(InputEventListener t1, InputEventListener t2) {
            return Integer.compare(t1.getPriority(), t2.getPriority());
        }
    }
}
