package sy.core;

import java.util.ArrayList;
import java.util.List;

import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnObject;

public abstract class Gameplay {
    private Player player;
    private int playerTurnId;
    private NodeGraphObject nodeGraphObject;


    public Gameplay(Player player) {
        this.player = player;
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
        return playerTurnId == player.getId();
    }

    public boolean canMove(PawnObject pawnObject, MapNode toNode, TicketType ticketType) {

        //do checks, used in client and server so implemented here
        //To do: check if pawn has enough tickets

        if (!pawnObject.hasEnoughTickets(ticketType)) {
            return false;
        }

        if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.BUS) && (ticketType == TicketType.BUS || ticketType == TicketType.BLACK_TICKET)) {
            return true;
        }
        if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.TAXI) && (ticketType == TicketType.TAXI || ticketType == TicketType.BLACK_TICKET)) {
            return true;
        }
       if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.UBAHN) && (ticketType == TicketType.UBAHN || ticketType == TicketType.BLACK_TICKET)) {
           return true;
        }
      return false;
    }
    public abstract void movePlayer(PawnObject pawnObject, MapNode toNode, TicketType ticketType);
}
