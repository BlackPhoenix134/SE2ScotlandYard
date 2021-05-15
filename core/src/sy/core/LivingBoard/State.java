package sy.core.LivingBoard;

import sy.gameObjects.GameObject;

public abstract class State<T extends GameObject> {
    protected StateMachine<T> stateMachine;

    public State(StateMachine<T> stateMachine) {
        this.stateMachine = stateMachine;
    }

    public abstract void transitionIn();
    public abstract void transitionOut();
    public abstract void update(float delta);
    public abstract void draw(float delta);
}
