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
            case HORSE:
                if (ticketDetective.bikeTickets > 0) {
                    ticketDetective.bikeTickets--;
                    return true;
                }
                break;
            case BIKE:
                if (ticketDetective.dragonTickets > 0) {
                    ticketDetective.dragonTickets--;
                    return true;
                }
                break;
            case DRAGON:
                if (ticketDetective.horseTickets > 0) {
                    ticketDetective.horseTickets--;
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
            case HORSE:
                if (ticketDetective.bikeTickets > 0) {
                    return true;
                }
                break;
            case BIKE:
                if (ticketDetective.dragonTickets > 0) {
                    return true;
                }
                break;
            case DRAGON:
                if (ticketDetective.horseTickets > 0) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
}
