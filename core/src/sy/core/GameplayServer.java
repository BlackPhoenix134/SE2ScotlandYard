package sy.core;


import com.badlogic.gdx.Gdx;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.connection.ServerHandler;
import sy.connection.packages.AddPawnObject;
import sy.connection.packages.ClientMoveRequest;
import sy.connection.packages.GameplayReady;
import sy.connection.packages.MovePlayerObject;
import sy.connection.packages.PlayerTurn;
import sy.connection.packages.RemoveTicket;
import sy.core.Tickets.DetectiveTickets;
import sy.core.Tickets.MisterXTickets;
import sy.core.Tickets.TicketType;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnDetectiveObject;
import sy.gameObjects.PawnMisterXObject;

public class GameplayServer extends Gameplay {
    private ServerHandler server;
    private Queue<Integer> turnIDs = new LinkedList<>();
    private int gameplaysReady = 1;

    public GameplayServer(Player player, List<Player> players, ServerHandler server, GameObjectManager gameObjectManager) {
        super(player, players, server.getCallbacks(), gameObjectManager);
        this.server = server;
        turnIDs.add(player.getConnectionId());

        callbacks.registerCallback(ClientMoveRequest.class, packageObj -> {
            ClientMoveRequest clientRequest = (ClientMoveRequest) packageObj;
            server.sendAll(new MovePlayerObject(clientRequest.playerObjNetId, clientRequest.newNodeId), true);
            server.sendAll(new RemoveTicket(clientRequest.playerObjNetId, clientRequest.ticketType), true);
            changeTurn();
        });

        callbacks.registerCallback(GameplayReady.class, packageObj -> {
            GameplayReady gameplayReady = (GameplayReady) packageObj;
            turnIDs.add(gameplayReady.playerNetID);
            gameplaysReady++;
            if (gameplaysReady == players.size()) {
                spawnPlayerPawns(nodeGraphObject);
                changeTurn();
            }
        });

        callbacks.registerCallback(AddPawnObject.class, packageObj -> {
            AddPawnObject addPawnObject = (AddPawnObject) packageObj;
            if (addPawnObject.isMisterX) {
                PawnMisterXObject playerPawn = gameObjectManager.create(PawnMisterXObject.class);
                playerPawn.setNetId(addPawnObject.netID);
                playerPawn.setTickets(new MisterXTickets(5, 2));
                playerPawn.setTexture(SYAssetManager.getAsset(AssetDescriptors.MONSTER1)); //Temporary, change to cam pic
                MapNode newMapNode = nodeGraphObject.getMapNodes().get(addPawnObject.nodeID);
                playerPawn.setMapNode(newMapNode);
                pawnMisterXObject = playerPawn;
                if(playerPawn.getNetId()== player.getConnectionId()){
                    playerPawnObject = playerPawn;
                }
            } else {
                PawnDetectiveObject playerPawn = gameObjectManager.create(PawnDetectiveObject.class);
                playerPawn.setNetId(addPawnObject.netID);
                playerPawn.setTickets(new DetectiveTickets(11, 8, 4));
                playerPawn.setTexture(SYAssetManager.getAsset(AssetDescriptors.MONSTER3)); //Temporary, change to cam pic
                MapNode newMapNode = nodeGraphObject.getMapNodes().get(addPawnObject.nodeID);
                playerPawn.setMapNode(newMapNode);
                pawnDetectiveObjectList.add(playerPawn);
                if(playerPawn.getNetId() == player.getConnectionId()){
                    playerPawnObject = playerPawn;
                }
            }
        });
    }

    public void changeTurn() {
        Integer nextPlayerID = turnIDs.poll();
        server.sendAll(new PlayerTurn(nextPlayerID), true);
        turnIDs.add(nextPlayerID);

    }

    @Override
    public void initialize(NodeGraphObject nodeGraphObject) {
        super.nodeGraphObject = nodeGraphObject;
    }

    private void spawnPlayerPawns(NodeGraphObject nodeGraphObject) {
        //MapNode randomNode = Collections.getRandomItem(nodeGraphObject.getMapNodes());
        MapNode randomNode = nodeGraphObject.getMapNodes().get(0);
        int id = players.get(0).getConnectionId();
        server.sendAll(new AddPawnObject(id, randomNode.getId(), true), true);

        for (int i = 1; i < players.size(); i++) {
            //randomNode = Collections.getRandomItem(nodeGraphObject.getMapNodes());
            randomNode = nodeGraphObject.getMapNodes().get(i);
            id = players.get(i).getConnectionId();
            server.sendAll(new AddPawnObject(id, randomNode.getId(), false), true);
        }
    }

    int test = 0;

    @Override
    public void movePlayer(MapNode newNode, TicketType ticketType) {

        if (test == 0) {
            ticketType = TicketType.DOUBLETURN_TICKET;
        } else
            ticketType = TicketType.BLACK_TICKET;

        test++;

        if (isLocalTurn()) {
            if (ticketType != TicketType.DOUBLETURN_TICKET) { //Make a move
                boolean move = canMove(newNode, ticketType);
                if (move) {
                    playerPawnObject.setMapNode(newNode);
                    server.sendAll(new MovePlayerObject(playerPawnObject, newNode), true);
                    server.sendAll(new RemoveTicket(playerPawnObject.getNetId(), ticketType), true);
                    if (pawnMisterXObject.turnSeries == 0) {
                        changeTurn();
                    }
                }
            } else {
                server.sendAll(new RemoveTicket(playerPawnObject.getNetId(), ticketType), true);
            }
        }
    }

    @Override
    public boolean canMove(MapNode toNode, sy.core.Tickets.TicketType ticketType) {

        if (!playerPawnObject.hasEnoughTickets(ticketType)) {
            return false;
        }

        if (nodeGraphObject.hasEdge(playerPawnObject.getMapNode().getId(), toNode.getId(), MoveType.HORSE) && (ticketType == sy.core.Tickets.TicketType.HORSE || ticketType == sy.core.Tickets.TicketType.BLACK_TICKET)) {
            return true;
        }
        if (nodeGraphObject.hasEdge(playerPawnObject.getMapNode().getId(), toNode.getId(), MoveType.BIKE) && (ticketType == sy.core.Tickets.TicketType.BIKE || ticketType == sy.core.Tickets.TicketType.BLACK_TICKET)) {
            return true;
        }
        if (nodeGraphObject.hasEdge(playerPawnObject.getMapNode().getId(), toNode.getId(), MoveType.DRAGON) && (ticketType == sy.core.Tickets.TicketType.DRAGON || ticketType == sy.core.Tickets.TicketType.BLACK_TICKET)) {
            return true;
        }
        return false;
    }
}
