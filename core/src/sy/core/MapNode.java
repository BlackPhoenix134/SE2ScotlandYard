package sy.core;

import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapNode {

    private Vector2 position = Vector2.Zero;
    private List<Edge> edges = new ArrayList<>();

    public MapNode() {

    }

    public MapNode(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Edge> getEdgesForMovement(MoveType moveType) {
        List<Edge> ret = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.isAllowedMove(moveType))
                ret.add(edge);
        }
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapNode)) return false;
        MapNode mapNode = (MapNode) o;
        return position.equals(mapNode.position);
    }
}
