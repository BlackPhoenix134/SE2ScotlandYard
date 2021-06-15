package sy.gameObjects;


import java.util.ArrayList;
import java.util.List;

import sy.core.Consumer;
import sy.rendering.RenderPipeline;

public abstract class GameObject {
    private String uuid;
    private boolean isAlive = true;
    private boolean shouldDraw = true;
    private List<Consumer<GameObject>> onDestroyedSubscribers = new ArrayList<>();

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

    public void subscribeOnDestroyed(Consumer<GameObject> callback) {
        onDestroyedSubscribers.add(callback);
    }

    public String getUuid() {
        return uuid;
    }

    GameObject(String uuid) {
        this.uuid = uuid;
    }

    List<Consumer<GameObject>> getOnDestroyedSubscribers() {
        return onDestroyedSubscribers;
    }

    public abstract void update(float delta);
    public abstract void draw(float delta, RenderPipeline pipeline);

    protected void onObjectDestroyed() {
    }

}
