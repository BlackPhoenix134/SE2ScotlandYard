package sy.core.LivingBoard;


import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.core.Visuals.AnimationController;
import sy.gameObjects.Critter;
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
        CritterSpawner spawner = new CritterSpawner(firstNode, 10, 2, gameObjectManager, animController);
        addSpawner(spawner);
        spawner.setSizeVariation(new Vector2(1.5f, 2f));
    }


    public void addSpawner(CritterSpawner critterSpawner) {
        critterSpawners.add(critterSpawner);
    }

    public void tick(float delta) {
        for(CritterSpawner spawner : critterSpawners)
            spawner.tick(delta);
    }
}
