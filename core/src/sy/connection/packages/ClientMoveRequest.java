package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;
import sy.core.MapNode;
import sy.gameObjects.PawnObject;

@NetworkPackage
public class ClientMoveRequest {
    public int playerObjNetId;
    public int newNodeId;

    public ClientMoveRequest() {
    }

    public ClientMoveRequest(PawnObject pawnObject, MapNode node) {
        this.playerObjNetId = pawnObject.getNetId();
        this.newNodeId = node.getId();
    }
}
