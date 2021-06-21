package sy.core;

import com.badlogic.gdx.math.Vector2;

import sy.core.Clickable;
import sy.core.Physics.Area2D;
import sy.input.prio.InputEvent;

public interface ClickableGameObject extends Clickable {
    Object getOwner();
}
