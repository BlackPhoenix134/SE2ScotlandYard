package sy.core;

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

    public boolean isLocalTurn() {
        return playerTurnId == player.getId();
    }

    public MoveType canMove(PawnObject pawnObject, MapNode toNode) {
        //do checks, used in client and server so implemented here
        //To do: check if pawn has enough tickets
        if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.BUS)) {
            return MoveType.BUS;
        } else if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.SHIP)) {
            return MoveType.SHIP;
        } else if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.TAXI)) {
            return MoveType.TAXI;
        } else if (nodeGraphObject.hasEdge(pawnObject.getIndex(), toNode.getId(), MoveType.UBAHN)) {
            return MoveType.UBAHN;
        }
        return null;
    }

    public abstract void movePlayer(PawnObject pawnObject, MapNode toNode);
}
