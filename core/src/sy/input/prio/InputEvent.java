package sy.input.prio;

public class InputEvent {
    private float x1;
    private float x2;
    private float x3;
    private float x4;
    private boolean isConsumed = false;

    public InputEvent(float x1, float x2, float x3, float x4) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
    }

    public float getX1() {
        return x1;
    }

    public float getX2() {
        return x2;
    }

    public float getX3() {
        return x3;
    }

    public float getX4() {
        return x4;
    }

    public boolean isConsumed() {
        return isConsumed;
    }
    public void setConsumed(boolean isConsumed) { this.isConsumed = isConsumed; }

}
