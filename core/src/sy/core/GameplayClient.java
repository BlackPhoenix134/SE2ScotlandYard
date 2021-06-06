package sy.core;

import com.badlogic.gdx.Gdx;

import java.util.List;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.connection.ClientHandler;
import sy.connection.packages.*;
import sy.core.Tickets.DetectiveTickets;
import sy.core.Tickets.MisterXTickets;
import sy.core.Tickets.TicketType;
import sy.gameObjects.*;

public class GameplayClient extends Gameplay {
    private ClientHandler client;

    public GameplayClient(Player player, List<Player> players, ClientHandler client, GameObjectManager gameObjectManager) {
        super(player, players, client.getCallbacks(), gameObjectManager);
        this.client = client;


        this.callbacks.registerCallback(PlayerTurn.class, packageObj -> {
            sy.connection.packages.PlayerTurn playerTurn = (PlayerTurn) packageObj;
            setPlayerTurnId(playerTurn.id);
            if (playerTurn.id == pawnMisterXObject.getNetId()){
                gameround++;
                if (gameround == 3 || gameround == 8 || gameround == 13 || gameround == 18 || gameround == 24){
                    pawnMisterXObject.setShouldDraw(true);
                }else {
                    pawnMisterXObject.setShouldDraw(false);
                }
            }
        });

        callbacks.registerCallback(RemoveTicket.class, packageObj -> {
            List<PawnObject> pawnObjectList = getPawnObjects();
            RemoveTicket ticketToRemove = (RemoveTicket) packageObj;
            for (PawnObject pawnObject : pawnObjectList) {
                if (pawnObject.getNetId() == ticketToRemove.netID) {
                    pawnObject.removeTicket(ticketToRemove.ticket);
                    break;
                }
            }
        });
        callbacks.registerCallback(AddPawnObject.class, packageObj -> {
            AddPawnObject addPawnObject = (AddPawnObject) packageObj;
            if (addPawnObject.isMisterX) {
                PawnMisterXObject playerPawn = gameObjectManager.create(PawnMisterXObject.class);
                playerPawn.setShouldDraw(false);
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
            if (newNode.getId() == pawnMisterXObject.getMapNode().getId()){
                client.send(new DetectivesWonRequest(playerPawnObject.getNetId()));
            }
        }
    }

    @Override
    public boolean canMove(MapNode toNode, TicketType ticketType) {

        for(PawnDetectiveObject detective : pawnDetectiveObjectList){
            if(detective.getMapNode().getId() == toNode.getId())
                return false;
        }

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
