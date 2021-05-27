package sy.core;

import sy.connection.ServerHandler;

public class LobbyLogicServer extends LobbyLogic {
    private ServerHandler serverHandler;

    public LobbyLogicServer(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
        this.callbacks = serverHandler.getCallbacks();
    }
}
