package sy.core.LivingBoard.States;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import sy.core.Globals;
import sy.core.LivingBoard.PathNode;
import sy.core.LivingBoard.StateMachines.State;
import sy.core.LivingBoard.StateMachines.StateMachineGameObj;
import sy.core.Visuals.AnimationController;

public class CritterIdleState extends State<StateMachineGameObj> {
    private float frameTimer = 0;
    private AnimationController animationController;
    private Vector2 switchStateTime = new Vector2(1, 1);
    private float nextSwitchTime = 1;
    private CritterStates nextState;

    public CritterIdleState(StateMachineGameObj stateMachine, AnimationController animationController, CritterStates nextState) {
        super(stateMachine);
        this.animationController = animationController;
        this.nextState = nextState;
    }


    public CritterIdleState(StateMachineGameObj stateMachine, AnimationController animationController, CritterStates nextState, Vector2 switchStateTime) {
        this(stateMachine, animationController, nextState);
        this.switchStateTime = switchStateTime;
    }

    @Override
    public void transitionIn() {
        stateMachine.getGameObject().getSprite().setRegion(animationController.getCurrentFrame());
        nextSwitchTime = getNewSwitchTime();
    }

    @Override
    public void transitionOut() {

    }

    @Override
    public void update(float delta) {
        frameTimer += delta;
        if(frameTimer > nextSwitchTime) {
            frameTimer = 0;
            stateMachine.transition(nextState.ordinal());
        }
    }

    @Override
    public void draw(float delta) {
    }

    private float getNewSwitchTime() {
        return Globals.getRandomFloat(switchStateTime.x, switchStateTime.y);
    }
}