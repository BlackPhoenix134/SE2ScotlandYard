package sy.core;

import com.badlogic.gdx.Gdx;

import sy.connection.ClientHandler;
import sy.connection.packages.PlayerJoinLobbyRequest;
import sy.screens.LobbyMenu;

public class LobbyLogicClient extends LobbyLogic {
    private ClientHandler clientHandler;

    public LobbyLogicClient(ClientHandler clientHandler, LobbyMenu lobbyMenu) {
        super(clientHandler.getCallbacks(), lobbyMenu);
        this.clientHandler = clientHandler;
    }

    public void sendJoinRequest() {
        int id =  clientHandler.getKryonetClient().getID();
        Gdx.app.log("FICKN", "FICKN");
        clientHandler.send(new PlayerJoinLobbyRequest(id));
    }
}
