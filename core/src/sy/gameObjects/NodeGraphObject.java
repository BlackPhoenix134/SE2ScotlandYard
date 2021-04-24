package sy.gameObjects;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.Collections;

import sy.Game;
import sy.core.MoveType;
import sy.core.NodeGraph;
import sy.rendering.RenderPipeline;

public class NodeGraphObject extends GameObject {
    private NodeGraph graph;

    NodeGraphObject(String uuid) {
        super(uuid);
        graph = new NodeGraph(Arrays.asList(new Vector2(0, 0), new Vector2(100, 0),
                new Vector2(0, 100),new Vector2(100, 100)));
        graph.addEdge(0, 1, MoveType.MOVE1);
        graph.addEdge(1, 2, MoveType.MOVE1);
        graph.addEdge(2, 3, MoveType.MOVE1);
        graph.addEdge(3, 1, MoveType.MOVE1);
    }

    @Override
    public void update(float delta) {
        
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {

    }
}
