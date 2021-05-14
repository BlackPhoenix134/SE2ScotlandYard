package sy.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.math.MathUtils;

import sy.core.Globals;
import sy.core.LivingBoard.PathNode;
import sy.core.Visuals.AnimationController;
import sy.rendering.RenderPipeline;

public class Critter extends GameObject {
    private float frameTimer;
    private AnimationController animationController;
    Sprite sprite;
    PathNode currPathNode;
    PathNode nextPathNode;

    private float lerpValue = 0;

    public Critter(String uuid){
        super(uuid);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void init(AnimationController animationController) {
        this.animationController = animationController;
        sprite = new Sprite(animationController.getCurrentFrame());
    }

    @Override
    public void update(float delta) {
        frameTimer += delta;
        if(frameTimer > animationController.getFrameTime()) {
            frameTimer = 0;
            sprite.setRegion(animationController.getNextFrame());
        }

        if(currPathNode != null && nextPathNode != null) {
            float newX = MathUtils.lerp(currPathNode.getPosition().x, nextPathNode.getPosition().x, lerpValue);
            float newY = MathUtils.lerp(currPathNode.getPosition().y, nextPathNode.getPosition().y, lerpValue);
            sprite.setPosition(newX, newY);
            lerpValue += getLerpIncrementor(currPathNode, nextPathNode) * delta;
            if(lerpValue >= 1) {
                lerpValue = 0;
                currPathNode = nextPathNode;
                nextPathNode = null;
                if(currPathNode.hasNextNode())
                    nextPathNode = currPathNode.getRandomNextNode();
                else
                    setAlive(false);
            }
        }
    }

    private float getLerpIncrementor(PathNode from, PathNode to) {
        return 1 / from.distanceTo(to) * 500;
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        pipeline.add(sprite, 1000);
    }

    public void follow(PathNode node) {
        currPathNode = node;
        nextPathNode = getNextPathNode(node);
    }

    private PathNode getNextPathNode(PathNode node) {
        return node.getRandomNextNode();
    }
}
