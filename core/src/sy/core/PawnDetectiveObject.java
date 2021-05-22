package sy.core;

import sy.gameObjects.PawnObject;

public class PawnDetectiveObject extends PawnObject {

    private TicketDetecitve ticketDetecitve;

    public PawnDetectiveObject(String uuid, TicketDetecitve ticketDetecitve) {
        super(uuid);
        setDetecitveTickets(ticketDetecitve);
    }

    public void setDetecitveTickets(TicketDetecitve ticketDetecitve) {
        this.ticketDetecitve = ticketDetecitve;
    }

    @Override
    public boolean removeTicket(TicketType type) {
        if (type == TicketType.BLACK_TICKET || type == TicketType.DOUBLETURN_TICKET) {
            return false;
        }

        switch (type) {
            case BUS:
                if (ticketDetecitve.getBusTickets() > 0) {
                    ticketDetecitve.setBusTickets(ticketDetecitve.getBusTickets() - 1);
                    return true;
                }
                break;
            case TAXI:
                if (ticketDetecitve.getTaxiTickets() > 0) {
                    ticketDetecitve.setTaxiTickets(ticketDetecitve.getTaxiTickets() - 1);
                    return true;
                }
                break;
            case UBAHN:
                if (ticketDetecitve.getUbahnTickets() > 0) {
                    ticketDetecitve.setUbahnTickets(ticketDetecitve.getUbahnTickets() - 1);
                    return true;
                }
                break;
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
                if (ticketDetecitve.getBusTickets() > 0) {
                    return true;
                }
                break;
            case TAXI:
                if (ticketDetecitve.getTaxiTickets() > 0) {
                    return true;
                }
                break;
            case UBAHN:
                if (ticketDetecitve.getUbahnTickets() > 0) {
                    return true;
                }
                break;
        }
        return false;
    }
}
