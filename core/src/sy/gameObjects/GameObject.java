package sy.gameObjects;


import sy.rendering.RenderPipeline;

public abstract class GameObject {
    private String uuid;
    private boolean isAlive = true;
    private boolean shouldDraw = true;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean getShouldDraw() {
        return shouldDraw;
    }

    public void setShouldDraw(boolean shouldDraw) {
        this.shouldDraw = shouldDraw;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getUuid() {
        return uuid;
    }

    GameObject(String uuid) {
        this.uuid = uuid;
    }

    public abstract void update(float delta);
    public abstract void draw(float delta, RenderPipeline pipeline);

    protected void onObjectDestroyed() {
    }

}
