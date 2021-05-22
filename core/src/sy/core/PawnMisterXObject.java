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

        if (type == TicketType.TAXI || type == TicketType.BUS || type == TicketType.UBAHN) {
            return true;
        }
        switch (type) {

            case BLACK_TICKET:
                if (ticketMisterX.getBlackTickets() > 0) {
                    ticketMisterX.setBlackTickets(ticketMisterX.getBlackTickets() - 1);
                    return true;
                }
                break;
            case DOUBLETURN_TICKET:
                if (ticketMisterX.getDoubleTurnTickets() > 0) {
                    ticketMisterX.setDoubleTurnTickets(ticketMisterX.getDoubleTurnTickets() - 1);
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean hasEnoughTickets(TicketType type) {

        if (type == TicketType.TAXI || type == TicketType.BUS || type == TicketType.UBAHN) {
            return true;
        }
        switch (type) {

            case BLACK_TICKET:
                if (ticketMisterX.getBlackTickets() > 0) {
                    return true;
                }
                break;
            case DOUBLETURN_TICKET:
                if (ticketMisterX.getDoubleTurnTickets() > 0) {
                    return true;
                }
                break;
        }
        return false;
    }
}
