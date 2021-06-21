package sy.core.LivingBoard.StateMachines;

import java.util.HashMap;
import java.util.Map;

public class StateMachine {
    private State currentState;
    private Map<Integer, State> states = new HashMap<>();

    public State getCurrentState() {
        return currentState;
    }

    public StateMachine() {
    }

    public void addState(int id, State state) {
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

    public <T extends StateMachine> State<T> getState(int idx) {
        return states.get(idx);
    }
}
