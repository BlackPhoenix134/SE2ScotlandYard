package sy.gameObjects;

import sy.rendering.RenderPipeline;

public class TestGameObject extends GameObject {
    public boolean isUpdateCalled;
    public boolean isDrawCalled;

    TestGameObject(String uuid) {
        super(uuid);
    }

    @Override
    public void update(float delta) {
        isUpdateCalled = true;
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        isDrawCalled = true;
    }
}