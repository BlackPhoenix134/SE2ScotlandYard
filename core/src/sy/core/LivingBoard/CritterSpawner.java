package sy.core.LivingBoard;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

import sy.core.Extensions.Collections;
import sy.core.Globals;
import sy.core.Visuals.AnimationController;
import sy.gameObjects.Critter;
import sy.gameObjects.GameObjectManager;

public class CritterSpawner {
    private float tickTimer;
    private float spawnThreshold;
    private float spawnChance;
    private PathNode path;
    private GameObjectManager goManager;
    private AnimationController animController;
    private Vector2 sizeVariation = new Vector2(1, 1);

    public void setSizeVariation(Vector2 sizeVariation) {
        this.sizeVariation = sizeVariation;
    }

    public CritterSpawner(PathNode path, float spawnThreshold, float spawnChance, GameObjectManager goManager, AnimationController animController) {
        this.path = path;
        this.spawnThreshold = spawnThreshold;
        this.spawnChance = spawnChance;
        this.goManager = goManager;
        this.animController = animController;
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
        critter.init(animController);
        critter.getSprite().setScale(Globals.getRandomFloat(sizeVariation.x, sizeVariation.y));
        critter.follow(path);
    }

    private boolean shouldSpawn() {
        return (Globals.RANDOM.nextFloat() * 100) <= spawnChance;
    }


}
