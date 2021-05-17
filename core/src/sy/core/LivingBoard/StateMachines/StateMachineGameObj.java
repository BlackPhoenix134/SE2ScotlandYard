package sy.core.LivingBoard.StateMachines;

import sy.gameObjects.Critter;

public class StateMachineGameObj extends StateMachine {
    private Critter gameObject;

    public Critter getGameObject() {
        return gameObject;
    }

    public void setGameObject(Critter gameObject) {
        this.gameObject = gameObject;
    }

    public StateMachineGameObj(Critter gameObject) {
        this.gameObject = gameObject;
    }
}
