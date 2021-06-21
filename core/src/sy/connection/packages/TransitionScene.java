package sy.connection.packages;

import sy.screens.AbstractScreen;

public class TransitionScene {
    public Class<AbstractScreen> screenClass;

    public TransitionScene() {
    }

    public TransitionScene(Class<AbstractScreen> screenClass) {
        this.screenClass = screenClass;
    }
}
