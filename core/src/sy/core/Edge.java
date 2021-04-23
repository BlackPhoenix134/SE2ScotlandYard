package sy.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Edge {
    private Set<MoveType> allowedMoves = new HashSet<>();
    private MapNode node1;
    private MapNode node2;

    public Edge(MapNode node1, MapNode node2) {
        this.node1 = node1;
        this.node2 = node2;
        node1.getEdges().add(this);
        node2.getEdges().add(this);
    }

    public void addAllowedMove(MoveType moveType) {
        allowedMoves.add(moveType);
    }

    public boolean isAllowedMove(MoveType moveType) {
        return allowedMoves.contains(moveType);
    }
}
