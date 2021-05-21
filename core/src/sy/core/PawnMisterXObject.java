package sy.core;

import sy.gameObjects.PawnObject;

public class PawnMisterXObject extends PawnObject {
    public PawnMisterXObject(String uuid) {
        super(uuid);
        setTickets(new Tickets(8, 11, 4, 5, 2));
    }
}
