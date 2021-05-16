package sy.core.LivingBoard;

import java.util.HashMap;
import java.util.Map;

import sy.gameObjects.GameObject;

public class StateMachine<T extends GameObject> {
    private T gameObject;
    private State<T> currentState;
    private Map<Integer, State<T>> states = new HashMap<>();

    

    public StateMachine(T gameObject) {
        this.gameObject = gameObject;
    }

    public void addState(int id, State<T> state) {
        states.put(id, state);
    }

    public void transition(int id) {
        if(currentState != null)
            currentState.transitionOut();
        currentState = states.get(id);
        currentState.transitionIn();
    }

    public void update(float delta) {
        if(currentState != null)
            currentState.update(delta);
    }

    public void draw(float delta) {
        if(currentState != null)
            currentState.draw(delta);
    }
}
