package sy.core;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.Map;

import sy.connection.NetworkPackageCallbacks;
import sy.connection.packages.CreatePlayer;
import sy.screens.LobbyMenu;

public abstract class LobbyLogic {
    protected NetworkPackageCallbacks callbacks;
    protected LobbyMenu lobbyMenu;
    protected Map<Integer, Player> currentPlayers = new HashMap<>();


    public LobbyLogic(NetworkPackageCallbacks callbacks, LobbyMenu lobbyMenu) {
        this.callbacks = callbacks;
        this.lobbyMenu = lobbyMenu;

        callbacks.registerCallback(CreatePlayer.class, packageObj -> {
            CreatePlayer createPlayer = (CreatePlayer)packageObj;
            currentPlayers.put(createPlayer.connectionId, new Player(createPlayer.connectionId));
            lobbyMenu.rebuildUi();
        });
    }


    public Map<Integer, Player> getCurrentPlayers() {
        return currentPlayers;
    }
}
