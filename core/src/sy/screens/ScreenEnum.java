package sy.screens;
import sy.screens.TitleScreen;
import sy.screens.GameScreen;
import sy.screens.AbstractScreen;


public enum ScreenEnum {
    MAIN_MENU {
        public AbstractScreen getScreen(Object... params) {
            return new TitleScreen();
        }
    },

    GAME {
        public AbstractScreen getScreen(Object... params) {
            return new GameScreen();
        }
    };

    public abstract AbstractScreen getScreen(Object... params);
}

