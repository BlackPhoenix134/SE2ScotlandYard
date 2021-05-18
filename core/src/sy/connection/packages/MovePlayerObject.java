package sy.connection.packages;

import sy.core.MapNode;
import sy.gameObjects.PlayerObject;

public class MovePlayerObject {
    public int playerObjectNetId;
    public int newNodeId;

    public MovePlayerObject(PlayerObject playerObject, MapNode newNode) {
        this.playerObjectNetId = playerObject.getNetId();
        this.newNodeId = newNode.getId();
    }
}
