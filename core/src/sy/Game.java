package sy;
import sy.screens.ScreenEnum;
import sy.screens.ScreenManager;



public class Game extends com.badlogic.gdx.Game {

    @Override
    public void create() {
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showScreen( ScreenEnum.MAIN_MENU);
    }

    @Override
    public void dispose() {

    }
}
