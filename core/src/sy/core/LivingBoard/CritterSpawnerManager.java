package sy.core.LivingBoard;


import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.core.Math.PoissonDiskSampler;
import sy.core.Math.Polygon;
import sy.core.Visuals.AnimationController;
import sy.gameObjects.GameObjectManager;

public class CritterSpawnerManager {
    private List<CritterSpawner> critterSpawners = new ArrayList<>();
    private GameObjectManager gameObjectManager;

    public CritterSpawnerManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
        initGhostSpawner1();
    }

    private void initGhostSpawner1() {
        PathNode firstNode = new PathNode(new Vector2(-3869.0806f,121.376686f));
        PathNode branchNode = new PathNode(new Vector2(-1701.136f,561.10126f));
        firstNode.addNode(branchNode);
        PathNode node = new PathNode(new Vector2(-1744.0857f,156.14557f));
        branchNode.addNode(node);
        PathNode node2 = new PathNode(new Vector2(-1467.9797f,-216.08641f));
        node.addNode(node2);
        node = new PathNode(new Vector2(-1380.0347f,-263.1267f));
        node2.addNode(node);
        node = new PathNode(new Vector2(632.0756f,1024.1769f));
        branchNode.addNode(node);
        node2 = new PathNode(new Vector2(1196.5593f,1169.3883f));
        node.addNode(node2);
        AnimationController animController =  new AnimationController(SYAssetManager.getAsset(AssetDescriptors.GHOST_WALKING),9, 1, 0.3f);
        BasicCritterSpawner spawner = new BasicCritterSpawner(firstNode, 10, 2, gameObjectManager, animController);
        addSpawner(spawner);
        spawner.setSizeVariation(new Vector2(1.5f, 2f));
    }

    private void initSpiderSpawner1() {
        Polygon polygon = new Polygon(Arrays.asList(
                new Vector2(-1097.8313f, 23.393494f), new Vector2(-1304.3176f, 123.03136f),
                new Vector2(-1339.7153f, 377.37015f), new Vector2(-1242.6996f, 467.17535f),
                new Vector2(-1110.286f, 548.45886f), new Vector2(-892.6559f, 446.1989f),
                new Vector2(-861.8468f, 378.68118f), new Vector2(-839.55945f, 156.4625f)));
        Vector2 center = polygon.getCenter();
        List<Vector2> points = PoissonDiskSampler.SampleCircle(center, 1000, 70);
        for (Vector2 point : points) {
            if (polygon.isInsidePoly(point)) {

            }
        }
    }

    public void addSpawner(BasicCritterSpawner critterSpawner) {
        critterSpawners.add(critterSpawner);
    }

    public void tick(float delta) {
        for(CritterSpawner spawner : critterSpawners)
            spawner.tick(delta);
    }
}
