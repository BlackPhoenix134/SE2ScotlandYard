package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;
import sy.core.Tickets.TicketType;

@NetworkPackage
public class RemoveTicket {
    public TicketType ticket;
    public int netID;

    public RemoveTicket(){

    }
    public RemoveTicket(int netID, TicketType ticket) {
        this.ticket = ticket;
        this.netID = netID;
    }

}
