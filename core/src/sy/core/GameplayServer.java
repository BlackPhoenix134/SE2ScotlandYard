package sy.core;


import java.util.LinkedList;
import java.util.Queue;

import sy.connection.ServerHandler;
import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.MovePlayerObject;
import sy.gameObjects.PawnObject;

public class GameplayServer extends Gameplay {
    private ServerHandler server;
    private Queue<Player> turnQueue = new LinkedList<>();

    public GameplayServer(Player player, ServerHandler server) {
        super(player, server.getCallbacks());
        this.server = server;

        callbacks.registerCallback(ClientMoveRequest.class, packageObj -> {
            ClientMoveRequest clientRequest = (ClientMoveRequest) packageObj;
            server.sendAll(new MovePlayerObject(clientRequest.playerObjNetId, clientRequest.newNodeId), true);
        });
    }

    public void changeTurn(){
        Player nextPlayer = turnQueue.poll();
        server.sendAll(new PlayerTurn(nextPlayer.getConnectionId()), true);
        turnQueue.add(nextPlayer);

    }

    public void playerConnected(Player player){
        turnQueue.add(player);
    }

    @Override
    public void movePlayer(PawnObject pawnObject, MapNode newNode, TicketType ticketType) {
      boolean move = canMove(pawnObject, newNode, ticketType);
        if(isLocalTurn() && move) {
            pawnObject.setMapNode(newNode);
            server.sendAll(new MovePlayerObject(pawnObject, newNode), true);
            server.sendTo(getPlayerTurnId(), new RemoveTicket(ticketType));
        }
    }
}
