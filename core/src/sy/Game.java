package sy;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import sy.assets.SYAssetManager;
import sy.input.InputHandler;
import sy.rendering.RenderPipeline;
import sy.screens.ExitScreen;
import sy.screens.GameScreen;
import sy.screens.MainMenuScreen;
import sy.screens.ScreenManager;



public class Game extends com.badlogic.gdx.Game {
    private ScreenManager screenManager;
    private RenderPipeline renderPipeline;
    private InputHandler inputHandler;
    private ExtendViewport viewport;
    private OrthographicCamera camera;

    @Override
    public void create() {
        SYAssetManager.loadAssets();
        inputHandler = new InputHandler();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 600, camera);
        renderPipeline = new RenderPipeline(new SpriteBatch(), camera, viewport);
        screenManager = new ScreenManager(this);
        screenManager.addScreen(new MainMenuScreen(renderPipeline, camera, screenManager, inputHandler));
        screenManager.addScreen(new GameScreen(renderPipeline, camera, screenManager, inputHandler));
        screenManager.showScreen(GameScreen.class);
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
