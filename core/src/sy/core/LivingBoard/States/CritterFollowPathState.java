package sy.core.LivingBoard.States;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import sy.core.LivingBoard.PathNode;
import sy.core.LivingBoard.StateMachines.State;
import sy.core.LivingBoard.StateMachines.StateMachineGameObj;
import sy.core.Math.GoodMath;
import sy.core.Visuals.AnimationController;

public class CritterFollowPathState extends State<StateMachineGameObj> {
    private float frameTimer = 0;
    private AnimationController animationController;
    private PathNode currPathNode;
    private PathNode nextPathNode;
    //private float lerpValue = 0;

    public CritterFollowPathState(StateMachineGameObj stateMachine, AnimationController animationController) {
        super(stateMachine);
        this.animationController = animationController;
    }

    @Override
    public void transitionIn() {
        stateMachine.getGameObject().getSprite().setRegion(animationController.getCurrentFrame());
    }

    @Override
    public void transitionOut() {

    }

    public void setPath(PathNode node) {
        currPathNode = node;
        nextPathNode = getNextPathNode(currPathNode);
    }

    @Override
    public void update(float delta) {
        frameTimer += delta;
        if(frameTimer > animationController.getFrameTime()) {
            frameTimer = 0;
            stateMachine.getGameObject().getSprite().setRegion(animationController.getNextFrame());
        }
        if(currPathNode != null && nextPathNode != null) {
           // float newX = MathUtils.lerp(currPathNode.getPosition().x, nextPathNode.getPosition().x, lerpValue);
            //float newY = MathUtils.lerp(currPathNode.getPosition().y, nextPathNode.getPosition().y, lerpValue);
            Sprite sprite = stateMachine.getGameObject().getSprite();
            Vector2 currentPos = new Vector2(sprite.getX(), sprite.getY());
            Vector2 newPos = GoodMath.moveTowards(currentPos, nextPathNode.getPosition(), delta * 200);
            stateMachine.getGameObject().getSprite().setPosition(newPos.x, newPos.y);
            if(GoodMath.equals(newPos, nextPathNode.getPosition(), 50)) {
                currPathNode = nextPathNode;
                nextPathNode = null;
                if(currPathNode.hasNextNode())
                    nextPathNode = currPathNode.getRandomNextNode();
                else
                    stateMachine.getGameObject().setAlive(false);
            }
        }
    }

    @Override
    public void draw(float delta) {
    }



    private float getLerpIncrementor(PathNode from, PathNode to) {
        return 1 / from.distanceTo(to) * 500;
    }

    private PathNode getNextPathNode(PathNode node) {
        return node.getRandomNextNode();
    }
}
