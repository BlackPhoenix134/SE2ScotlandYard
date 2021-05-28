package sy.core;

import sy.connection.ClientHandler;
import sy.connection.packages.ClientMoveRequest;
import sy.gameObjects.PawnObject;

public class GameplayClient extends Gameplay {
    private ClientHandler client;

    public GameplayClient(Player player, ClientHandler client) {
        super(player, client.getCallbacks());
        this.client = client;
    }

    @Override
    public void movePlayer(PawnObject pawnObject, MapNode newNode, TicketType ticketType) {
        boolean move = canMove(pawnObject, newNode, ticketType);
        if(isLocalTurn() && move) {
            pawnObject.setMapNode(newNode);
            client.send(new ClientMoveRequest(pawnObject, newNode));
        }
    }

    @Override
    public void removeTicket(PawnObject pawnObject, TicketType ticketToRemove) {
        pawnObject.removeTicket(ticketToRemove);
        client.send(new UpdateTickets(pawnObject.getNetId(), pawnObject.getTickets()));
    }
}
