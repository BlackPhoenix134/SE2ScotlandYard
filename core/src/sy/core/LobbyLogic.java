package sy.core;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.Map;

import sy.connection.NetworkPackageCallbacks;
import sy.connection.packages.CreateLobbyPlayer;
import sy.connection.packages.LobbyPlayerCustomTextureChanged;
import sy.connection.packages.LobbyPlayerReadySync;
import sy.screens.LobbyMenu;
import sy.screens.ScreenManager;

public abstract class LobbyLogic {
    protected NetworkPackageCallbacks callbacks;
    protected LobbyMenu lobbyMenu;
    protected Map<Integer, LobbyPlayer> currLobbyPlayers = new HashMap<>();
    protected ScreenManager screenManager;

    public LobbyLogic(NetworkPackageCallbacks callbacks, LobbyMenu lobbyMenu, ScreenManager screenManager) {
        this.callbacks = callbacks;
        this.lobbyMenu = lobbyMenu;
        this.screenManager = screenManager;

        callbacks.registerCallback(CreateLobbyPlayer.class, packageObj -> {
            CreateLobbyPlayer createLobbyPlayer = (CreateLobbyPlayer)packageObj;
            currLobbyPlayers.put(createLobbyPlayer.connectionId, new LobbyPlayer(createLobbyPlayer.connectionId));
            lobbyMenu.rebuildUi();
        });

        callbacks.registerCallback(LobbyPlayerReadySync.class, pckg -> {
            LobbyPlayerReadySync lobbyPlayerReadySync = (LobbyPlayerReadySync)pckg;
            LobbyPlayer lobbyPlayer = currLobbyPlayers.get(lobbyPlayerReadySync.connectionId);
            lobbyPlayer.setReady(lobbyPlayerReadySync.isReady);
            lobbyMenu.rebuildUi();
        });

        callbacks.registerCallback(LobbyPlayerCustomTextureChanged.class, pckg -> {
            LobbyPlayerCustomTextureChanged customTexturePackage = (LobbyPlayerCustomTextureChanged)pckg;
            Gdx.app.log("networking", "LobbyPlayerCustomTextureChanged" + customTexturePackage.connectionId + " " + customTexturePackage.customTexture);
            LobbyPlayer lobbyPlayer = currLobbyPlayers.get(customTexturePackage.connectionId);
            lobbyPlayer.setCustomTexture(customTexturePackage.customTexture);
        });
    }

    public abstract void setCustomTexture(byte[] customTexture);

    public abstract void readyUp();

    public Map<Integer, LobbyPlayer> getCurrLobbyPlayers() {
        return currLobbyPlayers;
    }

    public  boolean allReady() {
        for(LobbyPlayer lobbyPlayer : currLobbyPlayers.values()) {
            if(!lobbyPlayer.isReady())
                return false;
        }
        return true;
    }
}
