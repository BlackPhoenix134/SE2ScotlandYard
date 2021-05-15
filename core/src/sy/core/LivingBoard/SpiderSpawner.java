package sy.core.LivingBoard;

import com.badlogic.gdx.math.Vector2;

import java.awt.Point;

import sy.core.Extensions.Collections;
import sy.core.Math.Polygon;
import sy.core.Globals;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.SpiderCritter;

public class SpiderSpawner implements CritterSpawner {
    private float tickTimer;
    private float spawnThreshold;
    private float spawnChance;
    private Polygon polygon;
    private GameObjectManager goManager;
    private Vector2 sizeVariation = new Vector2(1, 1);
    private Point amountVariation = new Point(1, 1);

    public void setSizeVariation(Vector2 sizeVariation) {
        this.sizeVariation = sizeVariation;
    }

    public Vector2 getSizeVariation() {
        return sizeVariation;
    }

    public Point getAmountVariation() {
        return amountVariation;
    }

    public void setAmountVariation(Point amountVariation) {
        this.amountVariation = amountVariation;
    }

    public SpiderSpawner(Polygon polygon, float spawnThreshold, float spawnChance, GameObjectManager goManager) {
        this.polygon = polygon;
        this.spawnThreshold = spawnThreshold;
        this.spawnChance = spawnChance;
        this.goManager = goManager;
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
        int amount = Globals.getRandomInt(amountVariation.x, amountVariation.y);
        for(int i = 0; i < amount; i++) {
            Vector2 startPos = Collections.getRandomItem(polygon.getPoints());
            SpiderCritter critter = goManager.create(SpiderCritter.class);
            
            critter.getSprite().setScale(Globals.getRandomFloat(sizeVariation.x, sizeVariation.y));
            //critter.follow(path);
        }
    }

    private boolean shouldSpawn() {
        return (Globals.RANDOM.nextFloat() * 100) <= spawnChance;
    }


}
