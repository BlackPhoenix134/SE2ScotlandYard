package sy;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import sy.assets.SYAssetManager;
import sy.rendering.RenderPipeline;
import sy.screens.GameScreen;
import sy.screens.MainMenuScreen;
import sy.screens.ScreenManager;



public class Game extends com.badlogic.gdx.Game {
    private ScreenManager screenManager;
    private RenderPipeline renderPipeline;
    private ExtendViewport viewport;
    private OrthographicCamera camera;

    @Override
    public void create() {
        SYAssetManager.loadAssets();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 600, camera);
        renderPipeline = new RenderPipeline(new SpriteBatch(), camera, viewport);
        screenManager = new ScreenManager(this);
        screenManager.addScreen(new MainMenuScreen(renderPipeline, camera, screenManager));
        screenManager.addScreen(new GameScreen(renderPipeline, camera, screenManager));
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
