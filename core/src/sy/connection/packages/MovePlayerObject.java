package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;
import sy.core.MapNode;
import sy.gameObjects.PlayerObject;

@NetworkPackage
public class MovePlayerObject {
    public int playerObjectNetId;
    public int newNodeId;

    public MovePlayerObject(PlayerObject playerObject, MapNode newNode) {
        this.playerObjectNetId = playerObject.getNetId();
        this.newNodeId = newNode.getId();
    }
}
