package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;
import sy.core.MapNode;
import sy.core.Tickets.TicketType;
import sy.gameObjects.PawnObject;

@NetworkPackage
public class ClientMoveRequest {
    public int playerObjNetId;
    public int newNodeId;
    public TicketType ticketType;

    public ClientMoveRequest() {
    }

    public ClientMoveRequest(PawnObject pawnObject, MapNode node, TicketType ticketType) {
        this.playerObjNetId = pawnObject.getNetId();
        this.newNodeId = node.getId();
        this.ticketType = ticketType;
    }
}
