package sy.core;

import java.util.ArrayList;
import java.util.List;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.connection.NetworkPackageCallbacks;
import sy.connection.packages.AddPawnObject;
import sy.connection.packages.MovePlayerObject;
import sy.connection.packages.PlayerTurn;
import sy.connection.packages.RemoveTicket;
import sy.connection.packages.UpdateTickets;
import sy.core.Tickets.DetectiveTickets;
import sy.core.Tickets.MisterXTickets;
import sy.core.Tickets.TicketType;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnDetectiveObject;
import sy.gameObjects.PawnMisterXObject;
import sy.gameObjects.PawnObject;

public abstract class Gameplay {
    private Player player;
    private int playerTurnId;
    protected NodeGraphObject nodeGraphObject;
    protected NetworkPackageCallbacks callbacks;
    protected List<Player> players;
    protected GameObjectManager gameObjectManager;
    protected PawnMisterXObject pawnMisterXObject;
    protected List<PawnDetectiveObject> pawnDetectiveObjectList = new ArrayList<>();
    protected PawnObject playerPawnObject;


    public Gameplay(Player player, List<Player> players, NetworkPackageCallbacks callbacks, GameObjectManager gameObjectManager) {
        this.player = player;
        this.players = players;
        this.callbacks = callbacks;
        this.gameObjectManager = gameObjectManager;
        registerCallbacks();
    }

    public abstract void initialize(NodeGraphObject nodeGraphObject);

    private void registerCallbacks() {

        callbacks.registerCallback(MovePlayerObject.class, packageObj -> {
            List<PawnObject> pawnObjectList = getPawnObjects();
            MovePlayerObject playerMoved = (MovePlayerObject) packageObj;
            for (PawnObject player : pawnObjectList) {
                if (player.getNetId() == playerMoved.playerObjectNetId) {
                    MapNode newMapNode = nodeGraphObject.getMapNodes().get(playerMoved.newNodeId);
                    player.setMapNode(newMapNode);
                    break;
                }
            }
        });

        callbacks.registerCallback(RemoveTicket.class, packageObj -> {
            List<PawnObject> pawnObjectList = getPawnObjects();
            RemoveTicket ticketToRemove = (RemoveTicket) packageObj;
            for (PawnObject pawnObject : pawnObjectList) {
                if (pawnObject.getNetId() == player.getConnectionId()) {
                    removeTicket(pawnObject, ticketToRemove.getTicket());
                }
            }
        });

        callbacks.registerCallback(UpdateTickets.class, packageObj -> {
            UpdateTickets updatePlayer = (UpdateTickets) packageObj;
            if (updatePlayer.tickets instanceof DetectiveTickets){
                for (PawnDetectiveObject pawnDetectiveObject: pawnDetectiveObjectList){
                    if (pawnDetectiveObject.getNetId() == updatePlayer.netId){
                        pawnDetectiveObject.setTickets((DetectiveTickets) updatePlayer.tickets);
                    }
                }
            }else if (updatePlayer.tickets instanceof MisterXTickets){

                pawnMisterXObject.setTickets((MisterXTickets)updatePlayer.tickets);
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

        this.callbacks.registerCallback(sy.connection.packages.PlayerTurn.class, packageObj -> {
            sy.connection.packages.PlayerTurn playerTurn = (PlayerTurn) packageObj;
            setPlayerTurnId(playerTurn.index);
        });
    }

    public List<PawnObject> getPawnObjects() {
        List<PawnObject> pawnObjectList = new ArrayList<>();
        pawnObjectList.addAll(pawnDetectiveObjectList);
        pawnObjectList.add(pawnMisterXObject);
        return pawnObjectList;
    }

    public int getPlayerTurnId() {
        return playerTurnId;
    }

    public void setPlayerTurnId(int playerTurnId) {
        this.playerTurnId = playerTurnId;
    }

    public boolean isLocalTurn() {
        return playerTurnId == player.getConnectionId();
    }

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

    public abstract void movePlayer(MapNode toNode, sy.core.Tickets.TicketType ticketType);

    public abstract void removeTicket(PawnObject pawnObject, TicketType ticketToRemove);
}
