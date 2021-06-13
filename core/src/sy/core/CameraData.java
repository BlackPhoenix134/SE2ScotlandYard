package sy.core;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraData {
    private Vector2 dragValue = new Vector2();
    private Vector2 oldDragValue = new Vector2();
    private float currentScale = 0.25f;
    private float zoomValue = 1f;
    private OrthographicCamera orthographicCamera;

    public CameraData(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
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

    public void update(Rectangle boundingBox) {
        orthographicCamera.zoom = this.zoomValue;
        float scale = this.zoomValue * 2.0f;
        if (oldDragValue.x != dragValue.x || oldDragValue.y != dragValue.y) {
            orthographicCamera.position.add(-dragValue.x * scale, dragValue.y * scale, 0);
            oldDragValue.x = dragValue.x;
            oldDragValue.y = dragValue.y;
            if(boundingBox != null)
                orthographicCamera.position.set(clampCam(orthographicCamera.position, boundingBox));
        }
        orthographicCamera.update();
    }

    private Vector3 clampCam(Vector3 position, Rectangle boundingBox) {
        return new Vector3(clamp(position.x, boundingBox.x, boundingBox.width), clamp(position.y, boundingBox.y, boundingBox.height), position.z);
    }

    private float clamp(float value, float min, float max) {
        if(value < min)
            return min;
        return Math.min(value, max);
    }
}
