package sy.gameObjects;

import sy.core.Tickets.MisterXTickets;
import sy.core.Tickets.TicketType;

public class PawnMisterXObject extends PawnObject {

    private MisterXTickets tickets;

    public PawnMisterXObject(String uuid) {
        super(uuid);
    }

    public void setTickets(MisterXTickets tickets) {
        this.tickets = tickets;
    }

    public MisterXTickets getTickets() {
        return tickets;
    }

    @Override
    public boolean removeTicket(TicketType type) {

        if (type == TicketType.BIKE || type == TicketType.HORSE || type == TicketType.DRAGON) {
            return true;
        }
        switch (type) {

            case BLACK_TICKET:
                if (tickets.blackTickets > 0) {
                    tickets.blackTickets--;
                    return true;
                }
                break;
            case DOUBLETURN_TICKET:
                if (tickets.doubleTurnTickets > 0) {
                    tickets.doubleTurnTickets--;
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
                if (tickets.blackTickets > 0) {
                    return true;
                }
                break;
            case DOUBLETURN_TICKET:
                if (tickets.doubleTurnTickets > 0) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
}
