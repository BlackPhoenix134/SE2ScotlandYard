package sy.core;

public class TicketMisterX {

    private int blackTickets;
    private int doubleTurnTickets;

    public TicketMisterX(int blackTickets, int doubleTurnTickets) {
        this.blackTickets = blackTickets;
        this.doubleTurnTickets = doubleTurnTickets;
    }

    public int getDoubleTurnTickets() {
        return doubleTurnTickets;
    }

    public void setDoubleTurnTickets(int doubleTurnTickets) {
        this.doubleTurnTickets = doubleTurnTickets;
    }

    public int getBlackTickets() {
        return blackTickets;
    }

    public void setBlackTickets(int blackTickets) {
        this.blackTickets = blackTickets;
    }
}
