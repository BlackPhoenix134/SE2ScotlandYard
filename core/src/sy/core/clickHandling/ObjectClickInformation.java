package sy.core.clickHandling;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import sy.core.Clickable;
import sy.input.prio.InputEvent;

public class ObjectClickInformation {
    private Clickable clickable;
    private int priority;
    private boolean isUiSpace;
    private int id;

    public ObjectClickInformation(int id, Clickable clickable, int priority, boolean isUiSpace) {
        this.id = id;
        this.clickable = clickable;
        this.priority = priority;
        this.isUiSpace = isUiSpace;
    }

    Clickable getClickable() {
        return clickable;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isUiSpace() {
        return isUiSpace;
    }

    public boolean contains(InputEvent inputEvent, OrthographicCamera camera) {
        if(isUiSpace) {
            Vector2 clickedPos = new Vector2(inputEvent.getX1(), inputEvent.getFlippedX2());
            return clickable.getArea2D().intersects(new Vector2(clickedPos.x, clickedPos.y));
        } else {
            Vector3 unprojectedPos = camera.unproject(new Vector3(inputEvent.getX1(), inputEvent.getX2(), 0));
            Vector2 clickedPos = new Vector2(unprojectedPos.x, unprojectedPos.y);
            return clickable.getArea2D().intersects(new Vector2(clickedPos.x, clickedPos.y));
        }
    }

    public boolean contains(float x, float y, OrthographicCamera camera) {
        Vector2 clickedPos = new Vector2(x, y);
        if(!isUiSpace) {
            Vector3 unprojectedPos = camera.unproject(new Vector3(x, y, 0));
            clickedPos = new Vector2(unprojectedPos.x, unprojectedPos.y);
        }
        return clickable.getArea2D().intersects(new Vector2(clickedPos.x, clickedPos.y));
    }

    public boolean contains(Vector2 position, OrthographicCamera camera) {
        return contains(position.x, position.y, camera);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o instanceof ObjectClickInformation) {
            ObjectClickInformation other = (ObjectClickInformation) o;
            return this.hashCode() == other.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
