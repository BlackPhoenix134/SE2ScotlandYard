package sy.core;

import com.badlogic.gdx.math.Vector2;

import sy.core.Physics.Area2D;
import sy.input.prio.InputEvent;


public interface Clickable {
    void onClicked(InputEvent inputEvent);
    Vector2 getPosition();
    Area2D getArea2D();
}
