package sy.core;

import com.badlogic.gdx.Gdx;

import sy.connection.ServerHandler;
import sy.connection.packages.CreatePlayer;
import sy.connection.packages.PlayerJoinLobbyRequest;

public class LobbyLogicServer extends LobbyLogic {
    private ServerHandler serverHandler;

    public LobbyLogicServer(ServerHandler serverHandler) {
        super(serverHandler.getCallbacks());
        this.serverHandler = serverHandler;

        callbacks.registerCallback(PlayerJoinLobbyRequest.class, pckg -> {
            PlayerJoinLobbyRequest playerJoinLobbyRequest = (PlayerJoinLobbyRequest)pckg;
            for(Player player : currentPlayers.values()) {
                serverHandler.sendTo(playerJoinLobbyRequest.connectionId, new CreatePlayer(player.getConnectionId()));
            }
            serverHandler.sendAll(new CreatePlayer(playerJoinLobbyRequest.connectionId), true);
        });
    }
}
