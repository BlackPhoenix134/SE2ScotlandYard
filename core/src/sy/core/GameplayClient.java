package sy.core;

import com.badlogic.gdx.Gdx;

import java.util.List;

import sy.connection.ClientHandler;
import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.GameplayReady;
import sy.core.Tickets.TicketType;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NodeGraphObject;

public class GameplayClient extends Gameplay {
    private ClientHandler client;

    public GameplayClient(Player player, List<Player> players, ClientHandler client, GameObjectManager gameObjectManager) {
        super(player, players, client.getCallbacks(), gameObjectManager);
        this.client = client;
    }

    @Override
    public void initialize(NodeGraphObject nodeGraphObject) {
        super.nodeGraphObject = nodeGraphObject;
        client.send(new GameplayReady(player.getConnectionId()));
    }

    @Override
    public void movePlayer(MapNode newNode, TicketType ticketType) {
        boolean move = canMove(newNode, ticketType);
        if(isLocalTurn() && move) {
            playerPawnObject.setMapNode(newNode);
            client.send(new ClientMoveRequest(playerPawnObject, newNode, ticketType));
        }
    }
}
