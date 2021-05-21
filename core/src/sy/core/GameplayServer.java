package sy.core;

import sy.connection.ServerHandler;
import sy.connection.packages.MovePlayerObject;
import sy.gameObjects.PawnObject;

public class GameplayServer extends Gameplay {
    private ServerHandler server;

    public GameplayServer(Player player, ServerHandler server) {
        super(player);
        this.server = server;
    }

    @Override
    public void movePlayer(PawnObject pawnObject, MapNode newNode) {
        MoveType type = canMove(pawnObject, newNode);
        if(isLocalTurn() && type != null) {
            pawnObject.setMapNode(newNode); //Move to ClientHandler?
            pawnObject.removeTicket(type);
            server.sendAll(new MovePlayerObject(pawnObject, newNode), true);
        }
    }
}
