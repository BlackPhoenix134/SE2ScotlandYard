package sy.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import sy.connection.NetworkPackageCallbacks;
import sy.connection.ServerHandler;
import sy.core.Tickets.MisterXTickets;
import sy.core.Tickets.TicketType;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnMisterXObject;

import static org.junit.jupiter.api.Assertions.*;

class MovementMisterXTest {

    private MisterXTickets tickets;
    private PawnMisterXObject pawn;
    private GameplayServer gameplay;
    private NodeGraphObject nodeGraphObject;
    @BeforeEach
    void setUp() {
        nodeGraphObject = new NodeGraphObject("0");
        pawn = new PawnMisterXObject();
        tickets = new MisterXTickets(5,2);
        pawn.setTickets(tickets);
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(0));

        Player p = new Player(1);
        gameplay = new GameplayServer(p, null, new ServerHandler(new NetworkPackageCallbacks()), null);
        gameplay.setNodeGraphObject(nodeGraphObject);
        gameplay.setPlayerPawnObject(pawn);
    }

    @AfterEach
    void tearDown() {
        gameplay = null;
        pawn = null;
        tickets = null;
        nodeGraphObject = null;
    }

    @Test
    void canMoveSuccess() {
        assertTrue(gameplay.canMove(nodeGraphObject.getMapNodes().get(8), TicketType.BIKE));
    }

    @Test
    void canMoveNoEdge() {
        assertFalse(gameplay.canMove(nodeGraphObject.getMapNodes().get(20), TicketType.BIKE));
    }

    @Test
    void canMoveBikeSuccess() {
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(20));
        assertTrue(gameplay.canMove(nodeGraphObject.getMapNodes().get(9), TicketType.BIKE));
    }
    @Test
    void canMoveHorseSuccess() {
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(33));
        assertTrue(gameplay.canMove(nodeGraphObject.getMapNodes().get(21), TicketType.HORSE));
    }
    @Test
    void canMoveDragonSuccess() {
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(45));
        assertTrue(gameplay.canMove(nodeGraphObject.getMapNodes().get(12), TicketType.DRAGON));
    }
    @Test
    void canMoveBikeFailure() {
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(7));
        assertFalse(gameplay.canMove(nodeGraphObject.getMapNodes().get(31), TicketType.BIKE));
    }
    @Test
    void canMoveHorseFailure() {
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(0));
        assertFalse(gameplay.canMove(nodeGraphObject.getMapNodes().get(8), TicketType.HORSE));
    }
    @Test
    void canMoveDragonFailure() {
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(92));
        assertFalse(gameplay.canMove(nodeGraphObject.getMapNodes().get(77), TicketType.DRAGON));
    }
    @Test
    void canMoveBlackTicketSuccess(){
        assertTrue(gameplay.canMove(nodeGraphObject.getMapNodes().get(8), TicketType.BLACK_TICKET));
    }
    @Test
    void canMoveBlackTicketNoTickets(){
        pawn.setTickets(new MisterXTickets(0, 1));
        assertFalse(gameplay.canMove(nodeGraphObject.getMapNodes().get(8), TicketType.BLACK_TICKET));
    }

    @Test
    void canMoveDetectiveInTheWay(){
        assertFalse(gameplay.canMove(nodeGraphObject.getMapNodes().get(1), TicketType.BIKE));
    }

    @Test
    void ticketPossibilities1(){
        List<TicketType> tickets = new ArrayList<>();
        tickets.add(TicketType.DOUBLETURN_TICKET);
        tickets.add(TicketType.BLACK_TICKET);
        tickets.add(TicketType.BIKE);
        assertEquals(tickets, gameplay.getTicketPossibilities(pawn, nodeGraphObject.getMapNodes().get(8)));
    }
    @Test
    void ticketPossibilities2(){
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(184));
        List<TicketType> tickets = new ArrayList<>();
        tickets.add(TicketType.DOUBLETURN_TICKET);
        tickets.add(TicketType.BLACK_TICKET);
        tickets.add(TicketType.BIKE);
        tickets.add(TicketType.HORSE);
        assertEquals(tickets, gameplay.getTicketPossibilities(pawn, nodeGraphObject.getMapNodes().get(183)));
    }
    @Test
    void ticketPossibilitiesMissingTicket(){
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(184));
        pawn.setTickets(new MisterXTickets(0, 1));
        List<TicketType> tickets = new ArrayList<>();
        tickets.add(TicketType.DOUBLETURN_TICKET);
        tickets.add(TicketType.BIKE);
        tickets.add(TicketType.HORSE);
        assertEquals(tickets, gameplay.getTicketPossibilities(pawn, nodeGraphObject.getMapNodes().get(183)));
    }

    @Test
    void ticketPossibilitiesMissingTicket2(){
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(184));
        pawn.setTickets(new MisterXTickets(1, 0));
        List<TicketType> tickets = new ArrayList<>();
        tickets.add(TicketType.BLACK_TICKET);
        tickets.add(TicketType.BIKE);
        tickets.add(TicketType.HORSE);
        assertEquals(tickets, gameplay.getTicketPossibilities(pawn, nodeGraphObject.getMapNodes().get(183)));
    }
    @Test
    void ticketPossibilitiesMissingTicket3(){
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(184));
        pawn.setTickets(new MisterXTickets(0, 0));
        List<TicketType> tickets = new ArrayList<>();
        tickets.add(TicketType.BIKE);
        tickets.add(TicketType.HORSE);
        assertEquals(tickets, gameplay.getTicketPossibilities(pawn, nodeGraphObject.getMapNodes().get(183)));
    }
}