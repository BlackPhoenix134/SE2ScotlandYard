package sy.gameObjects;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import sy.rendering.RenderPipeline;

public class GameObjectManager {
    private Map<String, GameObject> gameObjects = new HashMap<>();

    public void update(float delta) {
        for(GameObject obj : gameObjects.values())
            obj.update(delta);
    }

    public void draw(float delta, RenderPipeline pipeline) {
        for(GameObject obj : gameObjects.values())
            obj.draw(delta, pipeline);
    }

    public <T extends GameObject> T create(Class<T> clazz) {
        String uniqueID = UUID.randomUUID().toString();
        //ToDo: proper exception handling
        try {
            GameObject obj = clazz.getDeclaredConstructor(String.class).newInstance(uniqueID);
            gameObjects.put(obj.getUuid(), obj);
            return (T) obj;
        } catch (Exception _) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends GameObject> T get(String uuid) {
        return (T)gameObjects.get(uuid);
    }
}
