package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;
import sy.core.MapNode;
import sy.gameObjects.PawnObject;

@NetworkPackage
public class MovePlayerObject {
    public int playerObjectNetId;
    public int newNodeId;

    public MovePlayerObject(PawnObject pawnObject, MapNode newNode) {
        this.playerObjectNetId = pawnObject.getNetId();
        this.newNodeId = newNode.getId();
    }
    public MovePlayerObject(int playerObjectNetId, int newNodeId) {
        this.playerObjectNetId = playerObjectNetId;
        this.newNodeId = newNodeId;
    }
}
