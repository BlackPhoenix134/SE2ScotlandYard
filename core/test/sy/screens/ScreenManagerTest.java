package sy.screens;

import com.badlogic.gdx.ScreenAdapter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sy.GameStart;
import sy.core.Consumer;
import sy.platforms.CameraPeripheral;

import static org.junit.jupiter.api.Assertions.*;

class ScreenManagerTest extends ScreenAdapter implements CameraPeripheral {
    private ScreenManager instance;
    private GameStart game;
    private AbstractScreen screen;

    @BeforeEach
    void setUp() {
        game = new GameStart(this);
        instance = new ScreenManager(game);
    }

    @Test
    void generalTest(){
        //constructor worked
        assertTrue(true);
    }

    @Test
    void screenHandlingTest(){ //cannot create abstract screen because of image loading..
        instance.addScreen(AbstractScreen.class, null);
        assertNull(instance.getScreen(AbstractScreen.class));
    }

    //cannot test show screen because of image loading..

    @AfterEach
    void tearDown() {
    }

    @Override
    public void startCamera() {

    }

    @Override
    public void setOnCameraResult(Consumer<byte[]> onCameraResult) {

    }
}