package sy.gameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import sy.Game;
import sy.core.MapNode;
import sy.core.MoveType;
import sy.core.NodeGraph;
import sy.rendering.RenderPipeline;

public class NodeGraphObject extends GameObject {
    private NodeGraph graph;
    //ToDo: scene2d or self made transform hierarchy, if this obejct moves, nodes do not move

   public NodeGraphObject(String uuid) {
        super(uuid);
    graph = new NodeGraph(Arrays.asList(new Vector2(0, 0), new Vector2(200, 0),
                new Vector2(0, 100),new Vector2(100, 100), new Vector2(200,300),
            new Vector2(400,400)));
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
        for(MapNode node : graph.getNodes()) {
            pipeline.getPrimitiveRenderer().drawCircle(node.getPosition(), 25, Color.BLACK, true);
        }

    }
}
