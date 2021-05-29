package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;
import sy.core.Tickets.TicketType;

@NetworkPackage
public class RemoveTicket {
    private TicketType ticket;

    public RemoveTicket(){

    }
    public RemoveTicket(TicketType ticket) {
        this.ticket = ticket;
    }

    public TicketType getTicket() {
        return ticket;
    }
}
