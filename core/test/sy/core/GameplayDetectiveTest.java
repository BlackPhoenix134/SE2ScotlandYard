package sy.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import sy.connection.ClientHandler;
import sy.connection.NetworkPackageCallbacks;
import sy.core.Tickets.DetectiveTickets;
import sy.core.Tickets.MisterXTickets;
import sy.core.Tickets.TicketType;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnDetectiveObject;
import sy.gameObjects.PawnMisterXObject;

import static org.junit.jupiter.api.Assertions.*;

class GameplayDetectiveTest {

    private GameplayClient gameplay;
    private PawnDetectiveObject pawn;
    private DetectiveTickets tickets;
    private List<PawnDetectiveObject> detectives = new ArrayList<>();
    private NodeGraphObject nodeGraphObject;

    @BeforeEach
    void setUp() {
        nodeGraphObject = new NodeGraphObject("0");
        pawn = new PawnDetectiveObject();
        tickets = new DetectiveTickets(11, 8, 4);
        pawn.setTickets(tickets);
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(0));

        PawnDetectiveObject detective = new PawnDetectiveObject(); //collegue
        detective.setMapNode(nodeGraphObject.getMapNodes().get(1));
        detectives.add(detective);

        gameplay = new GameplayClient(null, null, new ClientHandler(new NetworkPackageCallbacks()), null);
        gameplay.setNodeGraphObject(nodeGraphObject);
        gameplay.setPlayerPawnObject(pawn);
        gameplay.setPawnDetectives(detectives);

    }

    @AfterEach
    void tearDown() {
        gameplay = null;
        pawn = null;
        tickets = null;
        detectives = null;
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
    void canMoveBikeNoTickets() {
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(20));
        pawn.setTickets(new DetectiveTickets(0, 1, 1));
        assertFalse(gameplay.canMove(nodeGraphObject.getMapNodes().get(9), TicketType.BIKE));
    }
    @Test
    void canMoveHorseNoTickets() {
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(33));
        pawn.setTickets(new DetectiveTickets(1, 0, 1));
        assertFalse(gameplay.canMove(nodeGraphObject.getMapNodes().get(21), TicketType.HORSE));
    }
    @Test
    void canMoveDragonNoTickets() {
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(45));
        pawn.setTickets(new DetectiveTickets(1, 1, 0));
        assertFalse(gameplay.canMove(nodeGraphObject.getMapNodes().get(12), TicketType.DRAGON));
    }
    @Test
    void canMoveDetectiveInTheWay(){
        assertFalse(gameplay.canMove(nodeGraphObject.getMapNodes().get(1), TicketType.BIKE));
    }

    @Test
    void ticketPossibilities1(){
        List<TicketType> tickets = new ArrayList<>();
        tickets.add(TicketType.BIKE);
        assertEquals(tickets, gameplay.getTicketPossibilities(pawn, nodeGraphObject.getMapNodes().get(8)));
    }
    @Test
    void ticketPossibilities2(){
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(184));
        List<TicketType> tickets = new ArrayList<>();
        tickets.add(TicketType.BIKE);
        tickets.add(TicketType.HORSE);
        assertEquals(tickets, gameplay.getTicketPossibilities(pawn, nodeGraphObject.getMapNodes().get(183)));
    }

}