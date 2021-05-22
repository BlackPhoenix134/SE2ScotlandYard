package sy.core;

public class TicketDetecitve {
    private int busTickets;
    private int taxiTickets;
    private int ubahnTickets;


    public TicketDetecitve(int busTickets, int taxiTickets, int ubahnTickets) {
        this.busTickets = busTickets;
        this.taxiTickets = taxiTickets;
        this.ubahnTickets = ubahnTickets;
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

}
