package sy.core;

public class Tickets {
    private int busTickets;
    private int taxiTickets;
    private int ubahnTickets;
    private int blackTickets;
    private int doubleTurnTickets;

    public Tickets(int busTickets, int taxiTickets, int ubahnTickets, int blackTickets, int doubleTurnTickets) {
        this.busTickets = busTickets;
        this.taxiTickets = taxiTickets;
        this.ubahnTickets = ubahnTickets;
        this.blackTickets = blackTickets;
        this.doubleTurnTickets = doubleTurnTickets;
    }

    public int getDoubleTurnTickets() {
        return doubleTurnTickets;
    }

    public void setDoubleTurnTickets(int doubleTurnTickets) {
        this.doubleTurnTickets = doubleTurnTickets;
    }

    public int getBusTickets() {
        return busTickets;
    }

    public void setBusTickets(int busTickets) {
        this.busTickets = busTickets;
    }

    public int getTaxiTickets() {
        return taxiTickets;
    }

    public void setTaxiTickets(int taxiTickets) {
        this.taxiTickets = taxiTickets;
    }

    public int getUbahnTickets() {
        return ubahnTickets;
    }

    public void setUbahnTickets(int ubahnTickets) {
        this.ubahnTickets = ubahnTickets;
    }

    public int getBlackTickets() {
        return blackTickets;
    }

    public void setBlackTickets(int blackTickets) {
        this.blackTickets = blackTickets;
    }
}
