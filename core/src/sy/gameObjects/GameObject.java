package sy.gameObjects;

import sy.rendering.RenderPipeline;

public abstract class GameObject {
    private String uuid = null;
    private boolean isAlive = true;

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
