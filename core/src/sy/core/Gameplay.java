package sy.core;

import sy.connection.NetworkPackageCallbacks;
import sy.connection.packages.PlayerTurn;
import sy.core.Tickets.TicketType;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnObject;

public abstract class Gameplay {
    private Player player;
    private int playerTurnId;
    private NodeGraphObject nodeGraphObject;
    protected NetworkPackageCallbacks callbacks;


    public Gameplay(Player player, NetworkPackageCallbacks callbacks) {
        this.player = player;
        this.callbacks = callbacks;

        this.callbacks.registerCallback(sy.connection.packages.PlayerTurn.class, packageObj -> {
            sy.connection.packages.PlayerTurn playerTurn = (PlayerTurn) packageObj;
            setPlayerTurnId(playerTurn.index);
        });
    }

    public void initialize(NodeGraphObject nodeGraphObject){
        this.nodeGraphObject = nodeGraphObject;
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

    public boolean canMove(PawnObject pawnObject, MapNode toNode, sy.core.Tickets.TicketType ticketType) {

        //do checks, used in client and server so implemented here
        //To do: check if pawn has enough tickets

        if (!pawnObject.hasEnoughTickets(ticketType)) {
            return false;
        }

        if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.HORSE) && (ticketType == sy.core.Tickets.TicketType.HORSE || ticketType == sy.core.Tickets.TicketType.BLACK_TICKET)) {
            return true;
        }
        if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.BIKE) && (ticketType == sy.core.Tickets.TicketType.BIKE || ticketType == sy.core.Tickets.TicketType.BLACK_TICKET)) {
            return true;
        }
       if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.DRAGON) && (ticketType == sy.core.Tickets.TicketType.DRAGON || ticketType == sy.core.Tickets.TicketType.BLACK_TICKET)) {
           return true;
        }
      return false;
    }
    public abstract void movePlayer(PawnObject pawnObject, MapNode toNode, sy.core.Tickets.TicketType ticketType);

    public abstract void removeTicket(PawnObject pawnObject, TicketType ticketToRemove);
}
