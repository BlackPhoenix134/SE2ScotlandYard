package sy.core.Tickets;

public class DetectiveTickets extends Tickets {
    public int bikeTickets;
    public int dragonTickets;
    public int horseTickets;

    public DetectiveTickets(int bikeTickets, int horseTickets, int dragonTickets) {
        this.bikeTickets = bikeTickets;
        this.dragonTickets = dragonTickets;
        this.horseTickets = horseTickets;
    }

}
