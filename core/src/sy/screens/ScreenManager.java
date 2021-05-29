package sy.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.util.HashMap;
import java.util.Map;

import sy.ui.AliveButton;


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

    public void showScreen(final Class screenClass) {
        final AbstractScreen currentScreen = (AbstractScreen) game.getScreen();
        final AbstractScreen newScreen = screens.get(screenClass);
        newScreen.buildStage();

        if(currentScreen != null){
            game.setScreen(newScreen);
            newScreen.transitionIn();
            currentScreen.dispose();
            /*
            currentScreen.transitionOut(new Runnable(){
                @Override
                public void run() {
                    game.setScreen(newScreen);
                    newScreen.transitionIn();
                    currentScreen.dispose();
                }
            });*/
        }
        else{
            game.setScreen(newScreen);
            newScreen.transitionIn();
        }
    }

    public <T extends AbstractScreen> T getScreen(Class<T> screenClass) {
        return (T)screens.get(screenClass);
    }

}
