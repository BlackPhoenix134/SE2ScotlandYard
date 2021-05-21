package sy.core;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NodeGraph {
    private List<MapNode> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    public List<MapNode> getNodes() {
        return nodes;
    }

    public NodeGraph(List<Vector2> positions) {
        for(int i = 0; i < positions.size(); i++)
            addNode(positions.get(i), i);
    }

    public void addNode(Vector2 position, int id) {
        nodes.add(new MapNode(position, id));
    }

    public void addEdge(int nodeIdx1, int nodeIdx2, List<MoveType> allowedMoves) {
        Edge e = new Edge(nodes.get(nodeIdx1), nodes.get(nodeIdx2));
        e.addAllowedMove(allowedMoves);
    }

    public void addEdge(int nodeIdx1, int nodeIdx2, MoveType allowedMoves) {
        addEdge(nodeIdx1, nodeIdx2, Collections.singletonList(allowedMoves));
    }

    public boolean edgeExists(int nodeIdx1, int nodeIdx2, MoveType moveType) {
        MapNode mapNode = nodes.get(nodeIdx2);
        for (Edge edge : nodes.get(nodeIdx1).getEdges()) {
            if ((edge.getNode2().equals(mapNode) || edge.getNode1().equals(mapNode)) && edge.isAllowedMove(moveType) ) {
                return true;
            }
        }
        return false;

    }
}
