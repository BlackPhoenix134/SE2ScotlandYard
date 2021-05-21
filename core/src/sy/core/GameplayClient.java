package sy.core;

import sy.connection.ClientHandler;
import sy.connection.packages.ClientMoveRequest;
import sy.gameObjects.PawnObject;

public class GameplayClient extends Gameplay {
    private ClientHandler client;

    public GameplayClient(Player player, ClientHandler client) {
        super(player);
        this.client = client;
    }

    @Override
    public void movePlayer(PawnObject pawnObject, MapNode newNode) {
        MoveType type = canMove(pawnObject, newNode);
        if(isLocalTurn() && type != null) {
            pawnObject.setMapNode(newNode);
            pawnObject.removeTicket(type);
            client.send(new ClientMoveRequest(pawnObject, newNode));
        }
    }
}
