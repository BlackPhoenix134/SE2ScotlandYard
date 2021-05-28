package sy.core;

public class UpdateTickets {
    public Tickets tickets;
    public int netId;

    public UpdateTickets(int netId, Tickets tickets) {
        this.tickets = tickets;
        this.netId = netId;
    }
}
