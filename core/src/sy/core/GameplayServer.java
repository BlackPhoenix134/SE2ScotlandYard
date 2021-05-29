package sy.core;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import sy.connection.ServerHandler;
import sy.connection.packages.AddPawnObject;
import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.MovePlayerObject;
import sy.connection.packages.PlayerTurn;
import sy.connection.packages.RemoveTicket;
import sy.connection.packages.UpdateTickets;
import sy.core.Extensions.Collections;
import sy.core.Tickets.TicketType;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnDetectiveObject;
import sy.gameObjects.PawnMisterXObject;
import sy.gameObjects.PawnObject;

public class GameplayServer extends Gameplay {
    private ServerHandler server;
    private Queue<Player> turnQueue = new LinkedList<>();


    public GameplayServer(Player player, List<Player> players, ServerHandler server, GameObjectManager gameObjectManager) {
        super(player, players, server.getCallbacks(), gameObjectManager);
        this.server = server;

        callbacks.registerCallback(ClientMoveRequest.class, packageObj -> {
            ClientMoveRequest clientRequest = (ClientMoveRequest) packageObj;
            server.sendAll(new MovePlayerObject(clientRequest.playerObjNetId, clientRequest.newNodeId), true);
        });

        callbacks.registerCallback(UpdateTickets.class, packageObj ->{
            server.sendAll(packageObj, true);
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
    public void initialize(NodeGraphObject nodeGraphObject) {
        spawnPlayerPawns(nodeGraphObject);
    }

    private void spawnPlayerPawns(NodeGraphObject nodeGraphObject) {
        PawnMisterXObject pawnMisterXObject = gameObjectManager.create(PawnMisterXObject.class);
        MapNode randomNode = Collections.getRandomItem(nodeGraphObject.getMapNodes());
        pawnMisterXObject.setMapNode(randomNode);
        pawnMisterXObject.setNetId(players.get(0).getConnectionId());
        server.sendAll(new AddPawnObject(pawnMisterXObject.getNetId(),randomNode.getId(),true), false);

        for (int i = 1; i < players.size(); i++){
            PawnDetectiveObject playerPawn = gameObjectManager.create(PawnDetectiveObject.class);
            randomNode = Collections.getRandomItem(nodeGraphObject.getMapNodes());
            playerPawn.setMapNode(randomNode);
            playerPawn.setNetId(players.get(i).getConnectionId());
            server.sendAll(new AddPawnObject(playerPawn.getNetId(),randomNode.getId(),false), false);
        }
    }

    @Override
    public void movePlayer(PawnObject pawnObject, MapNode newNode, sy.core.Tickets.TicketType ticketType) {
      boolean move = canMove(pawnObject, newNode, ticketType);
        if(isLocalTurn() && move) {
            pawnObject.setMapNode(newNode);
            server.sendAll(new MovePlayerObject(pawnObject, newNode), true);
            server.sendTo(getPlayerTurnId(), new RemoveTicket(ticketType));
        }
    }

    @Override
    public void removeTicket(PawnObject pawnObject, TicketType ticketToRemove) {
        pawnObject.removeTicket(ticketToRemove);
        if (pawnObject instanceof PawnMisterXObject){
            PawnMisterXObject misterXplayer = (PawnMisterXObject) pawnObject;
            server.sendAll(new UpdateTickets(pawnObject.getNetId(), misterXplayer.getTickets()), true);
        }
        if (pawnObject instanceof PawnDetectiveObject){
            PawnDetectiveObject detectiveplayer = (PawnDetectiveObject) pawnObject;
            server.sendAll(new UpdateTickets(pawnObject.getNetId(), detectiveplayer.getTickets()), true);
        }
    }
}
