package sy.core;

import java.util.HashMap;
import java.util.Map;

import sy.gameObjects.GameObject;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NetworkIdentifiable;

public class NetworkObjectManager {
    private Map<Integer, NetworkIdentifiable> managedObjs = new HashMap<>();

    public <T extends NetworkIdentifiable> T get(int netId) {
        return (T)managedObjs.get(netId);
    }

    public <T extends NetworkIdentifiable> T add(int netId, T networkIdentifiable) {
        networkIdentifiable.setNetId(netId);
        managedObjs.put(netId, networkIdentifiable);
        return networkIdentifiable;
    }
}
