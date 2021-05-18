package sy.connection.packages;

import sy.core.MapNode;
import sy.gameObjects.PlayerObject;

public class ClientMoveRequest {
    public int playerObjNetId;
    public int newNodeId;

    public ClientMoveRequest(PlayerObject playerObject, MapNode node) {
        this.playerObjNetId = playerObject.getNetId();
        this.newNodeId = node.getId();
    }
}
