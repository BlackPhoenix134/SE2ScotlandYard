package sy.core.LivingBoard;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import sy.core.Extensions.Collections;

public class PathNode {
    private List<PathNode> nextNodes = new ArrayList<>();
    private Vector2 position;

    public PathNode(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void addNode(PathNode node) {
        nextNodes.add(node);
    }

    public void addNodes(List<PathNode> nodes) {
        nextNodes.addAll(nodes);
    }

    public float distanceTo(PathNode other) {
        return Vector2.dst(position.x, position.y, other.getPosition().x, other.getPosition().y);
    }

    public boolean hasNextNode() {
        return !(nextNodes == null || nextNodes.isEmpty());
    }

    public PathNode getRandomNextNode() {
        return Collections.getRandomItem(nextNodes);
    }
}
