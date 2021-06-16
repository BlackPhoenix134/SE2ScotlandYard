package sy.core.Physics;

import com.badlogic.gdx.math.Vector2;

public class BoundingBox implements Area2D {
    private float centerX;
    private float centerY;
    private float up;
    private float down;
    private float left;
    private float right;

    public BoundingBox(float centerX, float centerY, float up, float down, float left, float right) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public BoundingBox(float centerX, float centerY, float width, float height) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.left = width / 2;
        this.right = this.left;
        this.up = height / 2;
        this.down = this.up;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public float getUp() {
        return up;
    }

    public void setUp(float up) {
        this.up = up;
    }

    public float getDown() {
        return down;
    }

    public void setDown(float down) {
        this.down = down;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    @Override
    public boolean intersects(Vector2 position) {
        return position.x > centerX - left && position.x < centerX + right && position.y > centerY - up && position.y < centerY + down;
    }

}