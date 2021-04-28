package sy.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.util.HashMap;
import java.util.Map;


public class ScreenManager {
    private Game game;
    private Map<Class, AbstractScreen> screens = new HashMap<>();

    public ScreenManager(Game game) {
        this. game = game;
    }

    public void addScreen(Class screenClass, AbstractScreen screen) {
        screens.put(screenClass, screen);
    }

    public void addScreen(AbstractScreen screen) {
        screens.put(screen.getClass(), screen);
    }

    public void showScreen(Class screenClass) {
        Screen currentScreen = game.getScreen();
        AbstractScreen newScreen = screens.get(screenClass);
        newScreen.buildStage();
        game.setScreen(newScreen);

        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }

}
