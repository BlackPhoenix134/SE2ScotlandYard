package sy.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sy.connection.NetworkPackageCallbacks;
import sy.connection.ServerHandler;
import sy.core.Tickets.DetectiveTickets;
import sy.core.Tickets.MisterXTickets;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnDetectiveObject;
import sy.gameObjects.PawnMisterXObject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameplayTest {

    private MisterXTickets misterXTickets;
    private PawnMisterXObject pawnMisterXObject;
    private GameplayServer gameplay;
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

        Player p = new Player(1);
        gameplay = new GameplayServer(p, null, new ServerHandler(new NetworkPackageCallbacks()), null);
        gameplay.setNodeGraphObject(nodeGraphObject);
        gameplay.setPlayerPawnObject(pawnMisterXObject);
    }

    @AfterEach
    void tearDown() {
        gameplay = null;
        pawnMisterXObject = null;
        detectiveTickets = null;
        pawnDetectiveObject = null;
        detectives = null;
        misterXTickets = null;
        nodeGraphObject = null;
    }


    @Test
    void DetectiveHasBikeTicket() {
        detectiveTickets = new DetectiveTickets(1,0,0);
        pawnDetectiveObject.setTickets(detectiveTickets);
        assertTrue(gameplay.hasTickets(pawnDetectiveObject));
    }
    @Test
    void DetectiveHasHorseTicket() {
        detectiveTickets = new DetectiveTickets(0,1,0);
        pawnDetectiveObject.setTickets(detectiveTickets);
        assertTrue(gameplay.hasTickets(pawnDetectiveObject));
    }
    @Test
    void DetectiveHasDragonTicket() {
        detectiveTickets = new DetectiveTickets(0,0,1);
        pawnDetectiveObject.setTickets(detectiveTickets);
        assertTrue(gameplay.hasTickets(pawnDetectiveObject));
    }

    @Test
    void DetectiveHasTickets(){
        assertTrue(gameplay.hasTickets(pawnDetectiveObject));
    }

    @Test
    void DetectiveHasNoTickets(){
        detectiveTickets = new DetectiveTickets(0,0,0);
        pawnDetectiveObject.setTickets(detectiveTickets);
        assertFalse(gameplay.hasTickets(pawnDetectiveObject));
    }

    @Test
    void MisterXHasTickets(){
        assertTrue(gameplay.hasTickets(pawnMisterXObject));
    }

    @Test
    void MisterXAlwaysHasTickets(){
        misterXTickets = new MisterXTickets(0,0);
        assertTrue(gameplay.hasTickets(pawnMisterXObject));
    }
}