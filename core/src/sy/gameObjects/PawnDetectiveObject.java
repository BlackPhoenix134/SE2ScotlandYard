package sy.gameObjects;

import sy.core.Tickets.DetectiveTickets;
import sy.core.Tickets.TicketType;

public class PawnDetectiveObject extends PawnObject {

    private DetectiveTickets tickets;

    public PawnDetectiveObject(String uuid, DetectiveTickets tickets) {
        super(uuid, tickets);
        setDetectiveTicket(tickets);
    }

    public void setDetectiveTicket(DetectiveTickets ticketDetective) {
        this.tickets = ticketDetective;
    }

    public DetectiveTickets getTickets() {
        return tickets;
    }

    @Override
    public boolean removeTicket(TicketType type) {
        if (type == TicketType.BLACK_TICKET || type == TicketType.DOUBLETURN_TICKET) {
            return false;
        }

        switch (type) {
            case HORSE:
                if (tickets.bikeTickets > 0) {
                    tickets.bikeTickets--;
                    return true;
                }
                break;
            case BIKE:
                if (tickets.dragonTickets > 0) {
                    tickets.dragonTickets--;
                    return true;
                }
                break;
            case DRAGON:
                if (tickets.horseTickets > 0) {
                    tickets.horseTickets--;
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
                if (tickets.bikeTickets > 0) {
                    return true;
                }
                break;
            case BIKE:
                if (tickets.dragonTickets > 0) {
                    return true;
                }
                break;
            case DRAGON:
                if (tickets.horseTickets > 0) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
}
