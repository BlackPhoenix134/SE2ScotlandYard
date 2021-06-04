package sy.core;

import java.util.List;

import sy.connection.ClientHandler;
import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.UpdateTickets;
import sy.core.Tickets.TicketType;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnDetectiveObject;
import sy.gameObjects.PawnMisterXObject;
import sy.gameObjects.PawnObject;

public class GameplayClient extends Gameplay {
    private ClientHandler client;

    public GameplayClient(Player player, List<Player> players, ClientHandler client, GameObjectManager gameObjectManager) {
        super(player, players, client.getCallbacks(), gameObjectManager);
        this.client = client;
    }

    @Override
    public void initialize(NodeGraphObject nodeGraphObject) {
        super.nodeGraphObject = nodeGraphObject;
    }

    @Override
    public void movePlayer(MapNode newNode, TicketType ticketType) {
        boolean move = canMove(newNode, ticketType);
        if(isLocalTurn() && move) {
            playerPawnObject.setMapNode(newNode);
            client.send(new ClientMoveRequest(playerPawnObject, newNode));
        }
    }

    @Override
    public void removeTicket(PawnObject pawnObject, TicketType ticketToRemove) {
        pawnObject.removeTicket(ticketToRemove);
        if (pawnObject instanceof PawnMisterXObject){
            PawnMisterXObject misterXplayer = (PawnMisterXObject) pawnObject;
            client.send(new UpdateTickets(pawnObject.getNetId(), misterXplayer.getTickets()));
        }
        if (pawnObject instanceof PawnDetectiveObject){
            PawnDetectiveObject detectiveplayer = (PawnDetectiveObject) pawnObject;
            client.send(new UpdateTickets(pawnObject.getNetId(), detectiveplayer.getTickets()));
        }
    }
}
