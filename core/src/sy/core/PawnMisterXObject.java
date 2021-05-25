package sy.core;

import sy.gameObjects.PawnObject;

public class PawnMisterXObject extends PawnObject {

    private TicketMisterX ticketMisterX;

    public PawnMisterXObject(String uuid, TicketMisterX ticketMisterX) {
        super(uuid);
        setTicketMisterX(ticketMisterX);
    }

    public void setTicketMisterX(TicketMisterX ticketMisterX) {
        this.ticketMisterX = ticketMisterX;
    }

    @Override
    public boolean removeTicket(TicketType type) {

        if (type == TicketType.BIKE || type == TicketType.HORSE || type == TicketType.DRAGON) {
            return true;
        }
        switch (type) {

            case BLACK_TICKET:
                if (ticketMisterX.blackTickets > 0) {
                    ticketMisterX.blackTickets--;
                    return true;
                }
                break;
            case DOUBLETURN_TICKET:
                if (ticketMisterX.doubleTurnTickets > 0) {
                    ticketMisterX.doubleTurnTickets--;
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    @Override
    public boolean hasEnoughTickets(TicketType type) {

        if (type == TicketType.BIKE || type == TicketType.HORSE || type == TicketType.DRAGON) {
            return true;
        }
        switch (type) {

            case BLACK_TICKET:
                if (ticketMisterX.blackTickets > 0) {
                    return true;
                }
                break;
            case DOUBLETURN_TICKET:
                if (ticketMisterX.doubleTurnTickets > 0) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
}
