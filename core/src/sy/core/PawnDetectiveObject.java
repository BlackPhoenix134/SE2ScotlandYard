package sy.core;

import sy.gameObjects.PawnObject;

public class PawnDetectiveObject extends PawnObject {

    private TicketDetective ticketDetective;

    public PawnDetectiveObject(String uuid, TicketDetective ticketDetective) {
        super(uuid);
        setDetectiveTicket(ticketDetective);
    }

    public void setDetectiveTicket(TicketDetective ticketDetective) {
        this.ticketDetective = ticketDetective;
    }

    @Override
    public boolean removeTicket(TicketType type) {
        if (type == TicketType.BLACK_TICKET || type == TicketType.DOUBLETURN_TICKET) {
            return false;
        }

        switch (type) {
            case BUS:
                if (ticketDetective.busTickets > 0) {
                    ticketDetective.busTickets--;
                    return true;
                }
                break;
            case TAXI:
                if (ticketDetective.taxiTickets > 0) {
                    ticketDetective.taxiTickets--;
                    return true;
                }
                break;
            case UBAHN:
                if (ticketDetective.ubahnTickets > 0) {
                    ticketDetective.ubahnTickets--;
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
        if (type == TicketType.BLACK_TICKET || type == TicketType.DOUBLETURN_TICKET) {
            return false;
        }

        switch (type) {
            case BUS:
                if (ticketDetective.busTickets> 0) {
                    return true;
                }
                break;
            case TAXI:
                if (ticketDetective.taxiTickets> 0) {
                    return true;
                }
                break;
            case UBAHN:
                if (ticketDetective.ubahnTickets> 0) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
}
