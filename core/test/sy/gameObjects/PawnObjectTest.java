package sy.gameObjects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sy.core.Tickets.DetectiveTickets;
import sy.core.Tickets.MisterXTickets;
import sy.core.Tickets.TicketType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PawnObjectTest {
    private MisterXTickets misterXTickets;
    private PawnMisterXObject pawnMisterXObject;
    private NodeGraphObject nodeGraphObject;
    private PawnDetectiveObject pawnDetectiveObject;
    private DetectiveTickets detectiveTickets;
    private List<PawnDetectiveObject> detectives = new ArrayList<>();

    @BeforeEach
    void setUp() {
        nodeGraphObject = new NodeGraphObject("0");
        pawnMisterXObject = new PawnMisterXObject();
        misterXTickets = new MisterXTickets(5,2);
        pawnDetectiveObject = new PawnDetectiveObject();
        detectiveTickets = new DetectiveTickets(11,8,4);
        pawnMisterXObject.setTickets(misterXTickets);
        pawnMisterXObject.setMapNode(nodeGraphObject.getMapNodes().get(0));
        pawnDetectiveObject.setTickets(detectiveTickets);
        pawnDetectiveObject.setMapNode(nodeGraphObject.getMapNodes().get(1));

        PawnDetectiveObject detective = new PawnDetectiveObject(); //collegue
        detective.setMapNode(nodeGraphObject.getMapNodes().get(2));
        detectives.add(detective);
    }

    @AfterEach
    void tearDown() {
        pawnMisterXObject = null;
        detectiveTickets = null;
        pawnDetectiveObject = null;
        detectives = null;
        misterXTickets = null;
        nodeGraphObject = null;
    }


    @Test
    void detectiveHasEnoughBikeTickets(){
        pawnDetectiveObject.setTickets(new DetectiveTickets(1, 0, 0));
        assertTrue(pawnDetectiveObject.hasEnoughTickets(TicketType.BIKE));
    }
    @Test
    void detectiveHasEnoughHorseTickets(){
        pawnDetectiveObject.setTickets(new DetectiveTickets(0, 1, 0));
        assertTrue(pawnDetectiveObject.hasEnoughTickets(TicketType.HORSE));
    }
    @Test
    void detectiveHasEnoughDragonTickets(){
        pawnDetectiveObject.setTickets(new DetectiveTickets(0, 0, 1));
        assertTrue(pawnDetectiveObject.hasEnoughTickets(TicketType.DRAGON));
    }
    @Test
    void detectiveHasNotEnoughBikeTickets(){
        pawnDetectiveObject.setTickets(new DetectiveTickets(0, 1, 1));
        assertFalse(pawnDetectiveObject.hasEnoughTickets(TicketType.BIKE));
    }
    @Test
    void detectiveHasNotEnoughHorseTickets(){
        pawnDetectiveObject.setTickets(new DetectiveTickets(1, 0, 1));
        assertFalse(pawnDetectiveObject.hasEnoughTickets(TicketType.HORSE));
    }
    @Test
    void detectiveHasNotEnoughDragonTickets(){
        pawnDetectiveObject.setTickets(new DetectiveTickets(1, 1, 0));
        assertFalse(pawnDetectiveObject.hasEnoughTickets(TicketType.DRAGON));
    }
    @Test
    void detectiveHasEnoughTicketsWrongType(){
        assertFalse(pawnDetectiveObject.hasEnoughTickets(TicketType.BLACK_TICKET));
        assertFalse(pawnDetectiveObject.hasEnoughTickets(TicketType.DOUBLETURN_TICKET));
    }
    @Test
    void mrXHasEnoughBlackTickets(){
        pawnMisterXObject.setTickets(new MisterXTickets(1, 0));
        assertTrue(pawnMisterXObject.hasEnoughTickets(TicketType.BLACK_TICKET));
    }
    @Test
    void mrXHasNotEnoughBlackTickets(){
        pawnMisterXObject.setTickets(new MisterXTickets(0, 1));
        assertFalse(pawnMisterXObject.hasEnoughTickets(TicketType.BLACK_TICKET));
    }
    @Test
    void mrXHasEnoughDoubleTurnTickets(){
        pawnMisterXObject.setTickets(new MisterXTickets(0, 1));
        assertTrue(pawnMisterXObject.hasEnoughTickets(TicketType.DOUBLETURN_TICKET));
    }
    @Test
    void mrXHasNotEnoughDoubleTurnTickets(){
        pawnMisterXObject.setTickets(new MisterXTickets(1, 0));
        assertFalse(pawnMisterXObject.hasEnoughTickets(TicketType.DOUBLETURN_TICKET));
    }
    @Test
    void mrXHasInfiniteDefaultTickets(){
        pawnMisterXObject.setTickets(new MisterXTickets(0, 0));
        assertTrue(pawnMisterXObject.hasEnoughTickets(TicketType.BIKE));
        assertTrue(pawnMisterXObject.hasEnoughTickets(TicketType.HORSE));
        assertTrue(pawnMisterXObject.hasEnoughTickets(TicketType.DRAGON));
    }

    @Test
    void removeDetectiveBikeTicketSuccess() {
        detectiveTickets = new DetectiveTickets(1,0,0);
        pawnDetectiveObject.setTickets(detectiveTickets);
        assertTrue( pawnDetectiveObject.removeTicket(TicketType.BIKE));
        assertEquals(0, pawnDetectiveObject.getTickets().bikeTickets);
    }

    @Test
    void removeDetectiveHorseTicketSuccess() {
        detectiveTickets = new DetectiveTickets(0,1,0);
        pawnDetectiveObject.setTickets(detectiveTickets);
        assertTrue( pawnDetectiveObject.removeTicket(TicketType.HORSE));
        assertEquals(0, pawnDetectiveObject.getTickets().horseTickets);
    }
    @Test
    void removeDetectiveDragonTicketSuccess() {
        detectiveTickets = new DetectiveTickets(0,0,1);
        pawnDetectiveObject.setTickets(detectiveTickets);
        assertTrue( pawnDetectiveObject.removeTicket(TicketType.DRAGON));
        assertEquals(0, pawnDetectiveObject.getTickets().dragonTickets);
    }
    @Test
    void removeDetectiveBikeTicketFailure() {
        detectiveTickets = new DetectiveTickets(0,0,0);
        pawnDetectiveObject.setTickets(detectiveTickets);
        assertFalse( pawnDetectiveObject.removeTicket(TicketType.BIKE));
        assertEquals(0, pawnDetectiveObject.getTickets().bikeTickets);
    }

    @Test
    void removeDetectiveHorseTicketFailure() {
        detectiveTickets = new DetectiveTickets(0,0,0);
        pawnDetectiveObject.setTickets(detectiveTickets);
        assertFalse( pawnDetectiveObject.removeTicket(TicketType.HORSE));
        assertEquals(0, pawnDetectiveObject.getTickets().horseTickets);
    }

    @Test
    void removeDetectiveDragonTicketFailure() {
        detectiveTickets = new DetectiveTickets(0,0,0);
        pawnDetectiveObject.setTickets(detectiveTickets);
        assertFalse( pawnDetectiveObject.removeTicket(TicketType.DRAGON));
        assertEquals(0, pawnDetectiveObject.getTickets().dragonTickets);
    }

    @Test
    void removeWrongDetectiveTicketDoubleturn() {
        assertFalse( pawnDetectiveObject.removeTicket(TicketType.DOUBLETURN_TICKET));
    }

    @Test
    void removeWrongDetectiveTicketBlackTicket() {
        assertFalse( pawnDetectiveObject.removeTicket(TicketType.BLACK_TICKET));
    }

    @Test
    void hasEnoughTickets() {
    }

    @Test
    void removeMisterXDoubleTurnTicketSuccess() {
        misterXTickets = new MisterXTickets(0,1);
        pawnMisterXObject.setTickets(misterXTickets);
       assertTrue(pawnMisterXObject.removeTicket(TicketType.DOUBLETURN_TICKET));
       assertEquals(0, pawnMisterXObject.getTickets().doubleTurnTickets);
    }

    @Test
    void removeMisterXBlackTicketSuccess() {
        misterXTickets = new MisterXTickets(1,0);
        pawnMisterXObject.setTickets(misterXTickets);
        assertTrue(pawnMisterXObject.removeTicket(TicketType.BLACK_TICKET));
        assertEquals(0, pawnMisterXObject.getTickets().blackTickets);
    }

    @Test
    void removeMisterXDoubleTurnTicketFailure() {
        misterXTickets = new MisterXTickets(0,0);
        pawnMisterXObject.setTickets(misterXTickets);
        assertFalse(pawnMisterXObject.removeTicket(TicketType.DOUBLETURN_TICKET));
        assertEquals(0, pawnMisterXObject.getTickets().doubleTurnTickets);
    }
    @Test
    void removeMisterXBlackTicketFailure() {
        misterXTickets = new MisterXTickets(0,0);
        pawnMisterXObject.setTickets(misterXTickets);
        assertFalse(pawnMisterXObject.removeTicket(TicketType.BLACK_TICKET));
        assertEquals(0, pawnMisterXObject.getTickets().blackTickets);
    }

    @Test
    void removeMisterXBikeTicketSuccess() {
        misterXTickets = new MisterXTickets(0,0);
        pawnMisterXObject.setTickets(misterXTickets);
        assertTrue(pawnMisterXObject.removeTicket(TicketType.BIKE));
    }

    @Test
    void removeMisterXHorseTicketSuccess() {
        misterXTickets = new MisterXTickets(0,0);
        pawnMisterXObject.setTickets(misterXTickets);
        assertTrue(pawnMisterXObject.removeTicket(TicketType.HORSE));
    }

    @Test
    void removeMisterXDragonTicketSuccess() {
        misterXTickets = new MisterXTickets(0,0);
        pawnMisterXObject.setTickets(misterXTickets);
        assertTrue(pawnMisterXObject.removeTicket(TicketType.DRAGON));
    }


}