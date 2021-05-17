package sy.core.LivingBoard;

import com.badlogic.gdx.math.Vector2;


import sy.core.Function;
import sy.core.Globals;
import sy.core.LivingBoard.StateMachines.StateMachine;
import sy.gameObjects.Critter;
import sy.gameObjects.GameObjectManager;

public class BasicCritterSpawner implements CritterSpawner {
    private float tickTimer;
    private float spawnThreshold;
    private float spawnChance;
    private GameObjectManager goManager;
    private Function<Critter, StateMachine> critterInitializer;
    private Vector2 sizeVariation = new Vector2(1, 1);

    public void setSizeVariation(Vector2 sizeVariation) {
        this.sizeVariation = sizeVariation;
    }

    public BasicCritterSpawner(float spawnThreshold, float spawnChance, GameObjectManager goManager,
                               Function<Critter, StateMachine> critterInitializer) {
        this.spawnThreshold = spawnThreshold;
        this.spawnChance = spawnChance;
        this.goManager = goManager;
        this.critterInitializer = critterInitializer;
    }

    public void tick(float delta) {
        tickTimer += delta;

        if(tickTimer > spawnThreshold) {
            tickTimer = 0;
            if(shouldSpawn())
                spawn();
        }
    }

    private void spawn() {
        Critter critter = goManager.create(Critter.class);
        StateMachine stateMachine = critterInitializer.call(critter);
        critter.getSprite().setScale(Globals.getRandomFloat(sizeVariation.x, sizeVariation.y));
    }

    private boolean shouldSpawn() {
        return (Globals.RANDOM.nextFloat() * 100) <= spawnChance;
    }

}
