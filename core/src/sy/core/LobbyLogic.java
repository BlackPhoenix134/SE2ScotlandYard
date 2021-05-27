package sy.core;

import com.badlogic.gdx.Gdx;

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

            String str = "";
            for(Player p : getCurrentPlayers().values()) {
                str += "Player " + p.getConnectionId() + "\n";
            }
            Gdx.app.log("PLAYERS", "\n" + str);
        });
    }


    public Map<Integer, Player> getCurrentPlayers() {
        return currentPlayers;
    }
}
