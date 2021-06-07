package sy.core;


import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.connection.ServerHandler;
import sy.connection.packages.*;
import sy.core.Tickets.DetectiveTickets;
import sy.core.Tickets.MisterXTickets;
import sy.core.Tickets.TicketType;
import sy.gameObjects.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

        this.callbacks.registerCallback(PlayerTurn.class, packageObj -> {
            sy.connection.packages.PlayerTurn playerTurn = (PlayerTurn) packageObj;
            setPlayerTurnId(playerTurn.id);

        });

        callbacks.registerCallback(DetectivesWonRequest.class, packageObj -> {
            DetectivesWonRequest detectivesWonRequest = (DetectivesWonRequest) packageObj;
            server.sendAll(new DetectivesWon(detectivesWonRequest.netID), true);
        });

        callbacks.registerCallback(RemoveTicket.class, packageObj -> {
            List<PawnObject> pawnObjectList = getPawnObjects();
            RemoveTicket ticketToRemove = (RemoveTicket) packageObj;
            for (PawnObject pawnObject : pawnObjectList) {
                if (pawnObject.getNetId() == ticketToRemove.netID) {
                    pawnObject.removeTicket(ticketToRemove.ticket);
                    if (!hasTickets(pawnObject)){
                        server.sendAll(new DetectiveDies(pawnObject.getNetId()), true);
                        turnIDs.remove(turnIDs.size()-1);
                        if (turnIDs.size() == 1){
                            server.sendAll(new MisterXwon(pawnMisterXObject.getNetId()), true);
                        }
                    }
                    break;
                }
            }
        });

        callbacks.registerCallback(AddPawnObject.class, packageObj -> {
            AddPawnObject addPawnObject = (AddPawnObject) packageObj;
            if (addPawnObject.isMisterX) {
                PawnMisterXObject playerPawn = gameObjectManager.create(PawnMisterXObject.class);
                playerPawn.setNetId(addPawnObject.netID);
                playerPawn.setTickets(new MisterXTickets(5, 2));
                if(localPlayer.getCustomTexture() == null)
                    playerPawn.setTexture(SYAssetManager.getAsset(AssetDescriptors.MONSTER1));
                else
                    playerPawn.setTexture(new Texture(new Pixmap(localPlayer.getCustomTexture(), 0, localPlayer.getCustomTexture().length)));

                MapNode newMapNode = nodeGraphObject.getMapNodes().get(addPawnObject.nodeID);
                playerPawn.setMapNode(newMapNode);
                pawnMisterXObject = playerPawn;
                if (playerPawn.getNetId() == player.getConnectionId()) {
                    playerPawnObject = playerPawn;
                }
            } else {
                PawnDetectiveObject playerPawn = gameObjectManager.create(PawnDetectiveObject.class);
                playerPawn.setNetId(addPawnObject.netID);
                playerPawn.setTickets(new DetectiveTickets(2, 0, 0));
                if(localPlayer.getCustomTexture() == null)
                    playerPawn.setTexture(SYAssetManager.getAsset(AssetDescriptors.MONSTER3));
                else
                    playerPawn.setTexture(new Texture(new Pixmap(localPlayer.getCustomTexture(), 0, localPlayer.getCustomTexture().length)));
                MapNode newMapNode = nodeGraphObject.getMapNodes().get(addPawnObject.nodeID);
                playerPawn.setMapNode(newMapNode);
                pawnDetectiveObjectList.add(playerPawn);
                if (playerPawn.getNetId() == player.getConnectionId()) {
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

    public boolean hasTickets(PawnObject pawnObject){
        if (pawnObject instanceof PawnMisterXObject){
            return true;
        }else if(pawnObject instanceof  PawnDetectiveObject){
            if (!pawnObject.hasEnoughTickets(TicketType.BIKE) && !pawnObject.hasEnoughTickets(TicketType.HORSE) && !pawnObject.hasEnoughTickets(TicketType.DRAGON)) {
                return false;
            }
            return true;
        }
        return false;
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

                    for (PawnDetectiveObject pawnDetectiveObject : pawnDetectiveObjectList) {
                        if (newNode.getId() == pawnDetectiveObject.getMapNode().getId()) {
                            server.sendAll(new DetectivesWon(pawnMisterXObject.getNetId()));
                        }
                    }
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
