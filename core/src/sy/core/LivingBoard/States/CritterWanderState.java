package sy.core.LivingBoard.States;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import sy.core.Extensions.Collections;
import sy.core.LivingBoard.PathNode;
import sy.core.LivingBoard.StateMachines.State;
import sy.core.LivingBoard.StateMachines.StateMachineGameObj;
import sy.core.Visuals.AnimationController;

public class CritterWanderState extends State<StateMachineGameObj> {
    private AnimationController animationController;
    private float frameTimer = 0;
    private List<Vector2> points;
    private float speed = 200;

    private Vector2 fromPosition;
    private Vector2 toPosition;
    private Vector2 moveDirection;
    //private float lerpValue = 0;

    private CritterStates nextState;

    public CritterWanderState(StateMachineGameObj stateMachine, AnimationController animationController, List<Vector2> points, CritterStates nextState) {
        super(stateMachine);
        this.animationController = animationController;
        this.points = points;
        this.nextState = nextState;
    }

    @Override
    public void transitionIn() {
        stateMachine.getGameObject().getSprite().setRegion(animationController.getCurrentFrame());

        fromPosition = Collections.getRandomItem(points);
        toPosition = Collections.getRandomItem(points);
        moveDirection = toPosition.sub(fromPosition).nor();

        frameTimer = 0;
    }

    @Override
    public void transitionOut() {
        fromPosition = null;
        toPosition = null;
    }

    @Override
    public void update(float delta) {
        frameTimer += delta;
        if(fromPosition != null && toPosition != null) {
            if(frameTimer > animationController.getFrameTime()) {
                frameTimer = 0;
                stateMachine.getGameObject().getSprite().setRegion(animationController.getNextFrame());
            }
            float newX = stateMachine.getGameObject().getSprite().getX();
            float newY = stateMachine.getGameObject().getSprite().getY();
            newX += moveDirection.x * speed * delta;
            newY += moveDirection.y * speed * delta;
            stateMachine.getGameObject().getSprite().setPosition(newX, newY);
            if(Vector2.dst(newX, newY, toPosition.x, toPosition.y) < 50) {
                stateMachine.getGameObject().getSprite().setPosition(toPosition.x, toPosition.y);
                stateMachine.transition(nextState.ordinal());
            }
        }
    }

    @Override
    public void draw(float delta) {
    }
}
