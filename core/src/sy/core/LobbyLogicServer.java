package sy.core;

import java.util.ArrayList;
import java.util.List;

import sy.connection.ServerHandler;
import sy.connection.packages.CreateLobbyPlayer;
import sy.connection.packages.LobbyPlayerReady;
import sy.connection.packages.LobbyPlayerReadySync;
import sy.connection.packages.LobbyToStartGame;
import sy.connection.packages.PlayerJoinLobbyRequest;
import sy.screens.GameScreen;
import sy.screens.LobbyMenu;
import sy.screens.ScreenManager;

public class LobbyLogicServer extends LobbyLogic {
    private ServerHandler serverHandler;

    public LobbyLogicServer(ServerHandler serverHandler, LobbyMenu lobbyMenu, ScreenManager screenManager) {
        super(serverHandler.getCallbacks(), lobbyMenu, screenManager);
        this.serverHandler = serverHandler;

        callbacks.registerCallback(PlayerJoinLobbyRequest.class, pckg -> {
            PlayerJoinLobbyRequest playerJoinLobbyRequest = (PlayerJoinLobbyRequest)pckg;
            for(LobbyPlayer player : currLobbyPlayers.values()) {
                serverHandler.sendTo(playerJoinLobbyRequest.connectionId, new CreateLobbyPlayer(player.getConnectionId()));
            }
            for(LobbyPlayer player : currLobbyPlayers.values()) {
                serverHandler.sendTo(playerJoinLobbyRequest.connectionId, new LobbyPlayerReadySync(player.getConnectionId(), player.isReady()));
            }
            serverHandler.sendAll(new CreateLobbyPlayer(playerJoinLobbyRequest.connectionId), true);
        });

        callbacks.registerCallback(LobbyPlayerReady.class, pckg -> {
            LobbyPlayerReady lobbyPlayerReady = (LobbyPlayerReady)pckg;
            LobbyPlayer lobbyPlayer = currLobbyPlayers.get(lobbyPlayerReady.connectionId);
            serverHandler.sendAll(new LobbyPlayerReadySync(lobbyPlayer.getConnectionId(), !lobbyPlayer.isReady()), true);
        });


        callbacks.registerCallback(LobbyToStartGame.class, pckg -> {
            screenManager.showScreen(GameScreen.class);

            GameScreen gameScreen = screenManager.getScreen(GameScreen.class);
            List<Player> players = new ArrayList<>();
            Player localPlayer = null;
            for(LobbyPlayer lobbyPlayer : currLobbyPlayers.values()) {
                Player player = new Player(lobbyPlayer.getConnectionId());
                if(lobbyPlayer.getConnectionId() == 0) {
                    player.setLocalPlayer(true);
                    localPlayer = player;
                }
                players.add(player);
            }
            gameScreen.initialize(serverHandler, callbacks, players, localPlayer);
        });
    }

    public void startGame() {
        serverHandler.sendAll(new LobbyToStartGame(),true);
    }

    public void createSelf() {
        int id = 0;
        serverHandler.sendAll(new CreateLobbyPlayer(id), true);
    }

    @Override
    public void readyUp() {
        callbacks.invoke(new LobbyPlayerReady(0));
    }
}
