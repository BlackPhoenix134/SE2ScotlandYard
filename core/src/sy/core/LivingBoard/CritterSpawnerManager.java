package sy.core.LivingBoard;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.core.Extensions.Collections;
import sy.core.LivingBoard.StateMachines.StateMachineGameObj;
import sy.core.LivingBoard.States.CritterFollowPathState;
import sy.core.LivingBoard.States.CritterIdleState;
import sy.core.LivingBoard.States.CritterStates;
import sy.core.LivingBoard.States.CritterWanderState;
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
      //  initSpiderSpawner1();
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

        BasicCritterSpawner spawner = new BasicCritterSpawner(5, 100, gameObjectManager, (critter -> {
            AnimationController animationController =  new AnimationController(SYAssetManager.getAsset(AssetDescriptors.GHOST_WALKING),9, 1, 0.3f);
            critter.setSprite(new Sprite(animationController.getCurrentFrame()));

            StateMachineGameObj stateMachine = new StateMachineGameObj(critter);
            CritterFollowPathState critterFollowPathState = new CritterFollowPathState(stateMachine, animationController);
            critter.setStateMachine(stateMachine);
            critter.getSprite().setPosition(firstNode.getPosition().x, firstNode.getPosition().y);
            critterFollowPathState.setPath(firstNode);
            stateMachine.addState(CritterStates.FOLLOW_PATH.ordinal(), critterFollowPathState);
            stateMachine.transition(CritterStates.FOLLOW_PATH.ordinal());
            return stateMachine;
        }));
        addSpawner(spawner);
        spawner.setSizeVariation(new Vector2(1.5f, 2f));
    }

    private void initSpiderSpawner1() {
                Polygon polygon = new Polygon(Arrays.asList(
                        new Vector2(-844.0476f,-695.5206f),
                        new Vector2(-877.2617f,-72.0f),
                        new Vector2(-546.6297f,32.17176f),
                        new Vector2(-339.79602f,457.91705f),
                        new Vector2(51.225388f,620.9685f),
                        new Vector2(220.31572f,465.4657f),
                        new Vector2(277.68573f,92.56113f),
                        new Vector2(-80.1216f,-168.62306f),
                        new Vector2(-164.66676f,-401.12225f),
                        new Vector2(-600.9803f,-660.79675f)));
        Vector2 center = polygon.getCenter();
        List<Vector2> points = PoissonDiskSampler.SampleCircle(center, 1000, 50);
        List<Vector2> finalPoints = polygon.filterInside(points);
        BasicCritterSpawner spawner = new BasicCritterSpawner(5, 100, gameObjectManager, (critter -> {
            AnimationController animationController = new AnimationController(SYAssetManager.getAsset(AssetDescriptors.GHOST_WALKING),9, 1, 0.3f);
            critter.setSprite(new Sprite(animationController.getCurrentFrame()));
            Vector2 startPos = Collections.getRandomItem(finalPoints);
            critter.getSprite().setPosition(startPos.x, startPos.y);
            StateMachineGameObj stateMachine = new StateMachineGameObj(critter);
            critter.setStateMachine(stateMachine);

            CritterIdleState critterIdleState = new CritterIdleState(stateMachine, animationController, CritterStates.WANDER, new Vector2(1, 3));
            stateMachine.addState(CritterStates.IDLE.ordinal(), critterIdleState);

            CritterWanderState critterWanderState = new CritterWanderState(stateMachine, animationController, finalPoints, CritterStates.IDLE);
            stateMachine.addState(CritterStates.WANDER.ordinal(), critterWanderState);

            stateMachine.transition(CritterStates.IDLE.ordinal());
            return stateMachine;
        }));
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
