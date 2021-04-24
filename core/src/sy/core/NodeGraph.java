package sy.core;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NodeGraph {
    private List<MapNode> nodes = new ArrayList<>();

    public NodeGraph(List<Vector2> positions) {
        for(Vector2 position : positions)
            addNode(position);
    }

    public void addNode(Vector2 position) {
        nodes.add(new MapNode(position));
    }

    public void addEdge(int nodeIdx1, int nodeIdx2, List<MoveType> allowedMoves) {
        Edge e  = new Edge(nodes.get(nodeIdx1), nodes.get(nodeIdx2));
        e.addAllowedMove(allowedMoves);
    }

    public void addEdge(int nodeIdx1, int nodeIdx2, MoveType allowedMoves) {
        addEdge(nodeIdx1, nodeIdx2, Collections.singletonList(allowedMoves));
    }
}
