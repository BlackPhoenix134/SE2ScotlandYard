package sy.core;

import sy.connection.ClientHandler;
import sy.connection.packages.PlayerJoinLobbyRequest;

public class LobbyLogicClient extends LobbyLogic {
    private ClientHandler clientHandler;

    public LobbyLogicClient(ClientHandler clientHandler) {
        super(clientHandler.getCallbacks());
        this.clientHandler = clientHandler;
    }

    public void sendJoinRequest() {
        int id =  clientHandler.getKryonetClient().getID();
        clientHandler.send(new PlayerJoinLobbyRequest(id));
    }
}
