package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;
import sy.core.Tickets.Tickets;

@NetworkPackage
public class UpdateTickets {
    public Tickets tickets;
    public int netId;

    public UpdateTickets(int netId, Tickets tickets) {
        this.tickets = tickets;
        this.netId = netId;
    }
}
