package sy.core;

import sy.connection.ClientHandler;
import sy.connection.packages.ClientMoveRequest;
import sy.gameObjects.PlayerObject;

public class GameplayClient extends Gameplay {
    private ClientHandler client;

    public GameplayClient(Player player, ClientHandler client) {
        super(player);
        this.client = client;
    }

    @Override
    public void movePlayer(PlayerObject playerObject, MapNode newNode) {
        if(isLocalTurn() && canMove(playerObject, newNode)) {
            client.send(new ClientMoveRequest(playerObject, newNode));
        }
    }
}
