package sy.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import sy.core.Physics.Rectangle;

public class CameraData {
    private Vector2 dragValue = new Vector2();
    private Vector2 oldDragValue = new Vector2();
    private float currentScale = 0.25f;
    private float zoomValue = 1f;
    private OrthographicCamera orthographicCamera;
    private Rectangle rectangle;

    public CameraData(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
    }

    public CameraData(OrthographicCamera orthographicCamera, Rectangle rectangle) {
        this(orthographicCamera);
        this.rectangle = rectangle;
    }

    public Vector2 getDragValue() {
        return dragValue;
    }

    public void setDragValue(Vector2 dragValue) {
        this.dragValue = dragValue;
    }

    public Vector2 getOldDragValue() {
        return oldDragValue;
    }

    public void setOldDragValue(Vector2 oldDragValue) {
        this.oldDragValue = oldDragValue;
    }

    public float getCurrentScale() {
        return currentScale;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
    }

    public float getZoomValue() {
        return zoomValue;
    }

    public void setZoomValue(float zoomValue) {
        this.zoomValue = zoomValue;
    }

    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }

    public void setOrthographicCamera(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
    }

    public void update() {
        orthographicCamera.zoom = this.zoomValue;
        float scale = this.zoomValue * 2.0f;
        if (oldDragValue.x != dragValue.x || oldDragValue.y != dragValue.y) {
            orthographicCamera.position.add(-dragValue.x * scale, dragValue.y * scale, 0);
            oldDragValue.x = dragValue.x;
            oldDragValue.y = dragValue.y;
            if(rectangle != null)
                orthographicCamera.position.set(clampCam(orthographicCamera.position, rectangle));
        }
        orthographicCamera.update();
    }

    private Vector3 clampCam(Vector3 position, Rectangle rectangle) {
        return new Vector3(clamp(position.x, rectangle.getX(), rectangle.getWidth()),
                clamp(position.y, rectangle.getY(), rectangle.getHeight()),
                position.z);
    }

    private float clamp(float value, float min, float max) {
        if(value < min)
            return min;
        return Math.min(value, max);
    }
}
