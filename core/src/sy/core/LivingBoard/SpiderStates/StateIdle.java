package sy.core.LivingBoard.SpiderStates;

import sy.core.Globals;
import sy.core.LivingBoard.SpiderSpawner;
import sy.core.LivingBoard.State;
import sy.core.LivingBoard.StateMachine;
import sy.gameObjects.SpiderCritter;

public class StateIdle extends State<SpiderCritter> {
    private float timer = 0;
    private float idleSwitchTime = 0;

    public StateIdle(StateMachine<SpiderCritter> stateMachine) {
        super(stateMachine);
    }

    @Override
    public void transitionIn() {
        timer = 0;
        idleSwitchTime = createIdleSwitchTime();
    }

    @Override
    public void transitionOut() {

    }

    @Override
    public void update(float delta) {
        timer += delta;
        if(timer > 5) {
            timer = 0;
            idleSwitchTime = createIdleSwitchTime();
            stateMachine.transition(SpiderStates.WANDER.ordinal());
        }
    }

    @Override
    public void draw(float delta) {
        stateMachine.
    }

    private float createIdleSwitchTime() {
        return Globals.getRandomFloat(1f, 4f);
    }
}
