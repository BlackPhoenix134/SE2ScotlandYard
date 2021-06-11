package sy;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import sy.assets.SYAssetManager;
import sy.assets.ShaderManager;
import sy.platforms.CameraPeripheral;
import sy.rendering.RenderPipeline;
import sy.screens.GameEndDetectives;
import sy.screens.GameEndMrX;
import sy.screens.GameScreen;
import sy.screens.JoinGameMenu;
import sy.screens.LobbyMenu;
import sy.screens.MainMenuScreen;
import sy.screens.ScreenManager;



public class GameStart extends com.badlogic.gdx.Game {
    private ExtendViewport viewport;
    public RenderPipeline renderPipeline;
    public ShaderManager shaderManager = new ShaderManager();
    public CameraPeripheral cameraPeripheral;
    public  OrthographicCamera camera;

    public static GameStart Instance = null;


    public GameStart(CameraPeripheral cameraPeripheral) {
        super();
        Instance =  this;
        this.cameraPeripheral = cameraPeripheral;
    }

    @Override
    public void create() {
        SYAssetManager.loadAssets();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(5000, 3000, camera);
        renderPipeline = new RenderPipeline(new SpriteBatch(), new ShaderManager(), camera, viewport);
        ScreenManager screenManager = new ScreenManager(this);
        screenManager.addScreen(new MainMenuScreen(renderPipeline, camera, screenManager));
        screenManager.addScreen(new GameScreen(renderPipeline, camera, screenManager, shaderManager));
        screenManager.addScreen(new LobbyMenu(renderPipeline, camera, screenManager, cameraPeripheral));
        screenManager.addScreen(new JoinGameMenu(renderPipeline, camera, screenManager));
        screenManager.addScreen(new GameEndMrX(renderPipeline, camera, screenManager));
        screenManager.addScreen(new GameEndDetectives(renderPipeline, camera, screenManager));
        screenManager.showScreen(MainMenuScreen.class);
    }

    @Override
    public void dispose() {
        SYAssetManager.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        renderPipeline.updateBatchMatrix();
    }
}
