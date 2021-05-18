package sy.core;

import sy.connection.ClientHandler;
import sy.connection.ServerHandler;
import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.MovePlayerObject;
import sy.gameObjects.PlayerObject;

public class GameplayServer extends Gameplay {
    private ServerHandler server;

    public GameplayServer(Player player, ServerHandler server) {
        super(player);
        this.server = server;
    }

    @Override
    public void movePlayer(PlayerObject playerObject, MapNode newNode) {
        if(isLocalTurn() && canMove(playerObject, newNode)) {
            server.sendAll(new MovePlayerObject(playerObject, newNode), true);
        }
    }
}
