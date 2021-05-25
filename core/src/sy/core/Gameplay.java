package sy.core;

import com.esotericsoftware.kryonet.Connection;

import sy.connection.NetworkPackageCallbacks;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnObject;

public abstract class Gameplay {
    private Player player;
    private Connection playerTurnId;
    private NodeGraphObject nodeGraphObject;
    protected NetworkPackageCallbacks callbacks;


    public Gameplay(Player player, NetworkPackageCallbacks callbacks) {
        this.player = player;
        this.callbacks = callbacks;

        this.callbacks.registerCallback(PlayerTurn.class, packageObj -> {
            PlayerTurn playerTurn = (PlayerTurn) packageObj;
            setPlayerTurnId(playerTurn.getIndex());
        });
    }

    public void initialize(NodeGraphObject nodeGraphObject){
        this.nodeGraphObject = nodeGraphObject;
    }

    public Connection getPlayerTurnId() {
        return playerTurnId;
    }

    public void setPlayerTurnId(Connection playerTurnId) {
        this.playerTurnId = playerTurnId;
    }

    public boolean isLocalTurn() {
        return playerTurnId == player.getIndex();
    }

    public boolean canMove(PawnObject pawnObject, MapNode toNode, TicketType ticketType) {

        //do checks, used in client and server so implemented here
        //To do: check if pawn has enough tickets

        if (!pawnObject.hasEnoughTickets(ticketType)) {
            return false;
        }

        if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.HORSE) && (ticketType == TicketType.HORSE || ticketType == TicketType.BLACK_TICKET)) {
            return true;
        }
        if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.BIKE) && (ticketType == TicketType.BIKE || ticketType == TicketType.BLACK_TICKET)) {
            return true;
        }
       if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.DRAGON) && (ticketType == TicketType.DRAGON || ticketType == TicketType.BLACK_TICKET)) {
           return true;
        }
      return false;
    }
    public abstract void movePlayer(PawnObject pawnObject, MapNode toNode, TicketType ticketType);
}
