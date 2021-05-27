package sy.core;

import sy.connection.ClientHandler;

public class LobbyLogicClient extends LobbyLogic {
    private ClientHandler clientHandler;

    public LobbyLogicClient(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.callbacks = clientHandler.getCallbacks();
    }
}
