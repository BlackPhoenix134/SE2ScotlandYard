package sy.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

import sy.connection.ClientHandler;
import sy.connection.packages.LobbyPlayerCustomTextureChanged;
import sy.connection.packages.LobbyPlayerCustomTextureChangedRequest;
import sy.connection.packages.LobbyPlayerReady;
import sy.connection.packages.LobbyToStartGame;
import sy.connection.packages.PlayerJoinLobbyRequest;
import sy.screens.GameScreen;
import sy.screens.LobbyMenu;
import sy.screens.ScreenManager;

public class LobbyLogicClient extends LobbyLogic {
    private ClientHandler clientHandler;

    public LobbyLogicClient(ClientHandler clientHandler, LobbyMenu lobbyMenu, ScreenManager screenManager) {
        super(clientHandler.getCallbacks(), lobbyMenu, screenManager);
        this.clientHandler = clientHandler;


        callbacks.registerCallback(LobbyToStartGame.class, pckg -> {
            GameScreen gameScreen = screenManager.getScreen(GameScreen.class);
            List<Player> players = new ArrayList<>();
            Player localPlayer = null;
            for(LobbyPlayer lobbyPlayer : currLobbyPlayers.values()) {
                Player player = new Player(lobbyPlayer.getConnectionId(), lobbyPlayer.getCustomTexture(), lobbyPlayer.getName());
                if(lobbyPlayer.getConnectionId() == clientHandler.getKryonetClient().getID()) {
                    player.setLocalPlayer(true);
                    localPlayer = player;
                }
                players.add(player);
            }
            gameScreen.initialize(clientHandler, callbacks, players, localPlayer);
            screenManager.showScreen(GameScreen.class);
        });
    }

    public void sendJoinRequest(String name) {
        int id =  clientHandler.getKryonetClient().getID();
        clientHandler.send(new PlayerJoinLobbyRequest(id, name));
    }

    @Override
    public void setCustomTexture(byte[] customTexture) {
        clientHandler.send(new LobbyPlayerCustomTextureChangedRequest(clientHandler.getKryonetClient().getID(), customTexture));
    }

    @Override
    public void readyUp() {
        clientHandler.send(new LobbyPlayerReady(clientHandler.getKryonetClient().getID()));
    }
}
