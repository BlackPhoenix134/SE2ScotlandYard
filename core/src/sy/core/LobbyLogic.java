package sy.core;

import java.util.HashMap;
import java.util.Map;

import sy.connection.NetworkPackageCallbacks;
import sy.connection.packages.CreatePlayer;

public abstract class LobbyLogic {
    protected NetworkPackageCallbacks callbacks;
    protected Map<Integer, Player> currentPlayers = new HashMap<>();


    public LobbyLogic(NetworkPackageCallbacks callbacks) {
        this.callbacks = callbacks;

        callbacks.registerCallback(CreatePlayer.class, packageObj -> {
            CreatePlayer createPlayer = (CreatePlayer)packageObj;
            currentPlayers.put(createPlayer.connectionId, new Player(createPlayer.connectionId));
        });
    }


    public Map<Integer, Player> getCurrentPlayers() {
        return currentPlayers;
    }
}
