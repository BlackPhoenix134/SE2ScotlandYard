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
    }

    @Test
    void detectiveCanMoveSuccess() {
        pawn.setMapNode(nodeGraphObject.getMapNodes().get(0));
        assertTrue(gameplay.canMove(nodeGraphObject.getMapNodes().get(8), TicketType.BIKE));
    }
}