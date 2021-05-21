package sy.core;

import sy.gameObjects.PawnObject;

public class PawnDetectiveObject extends PawnObject {
    public PawnDetectiveObject(String uuid) {
        super(uuid);
        setTickets(new Tickets(8, 11, 4, 0, 0));
    }

}
