package sy;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import sy.assets.SYAssetManager;
import sy.assets.ShaderManager;
import sy.rendering.RenderPipeline;
import sy.screens.GameScreen;
import sy.screens.HostGameMenu;
import sy.screens.LobbyMenu;
import sy.screens.MainMenuScreen;
import sy.screens.ScreenManager;



public class Game extends com.badlogic.gdx.Game {
    private ExtendViewport viewport;
    private RenderPipeline renderPipeline;
    @Override
    public void create() {
        SYAssetManager.loadAssets();
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new ExtendViewport(5000, 3000, camera);
        renderPipeline = new RenderPipeline(new SpriteBatch(), new ShaderManager(), camera, viewport);
        ScreenManager screenManager = new ScreenManager(this);
        screenManager.addScreen(new MainMenuScreen(renderPipeline, camera, screenManager));
        screenManager.addScreen(new GameScreen(renderPipeline, camera, screenManager));
        screenManager.addScreen(new LobbyMenu(renderPipeline, camera, screenManager));
        screenManager.addScreen(new HostGameMenu(renderPipeline, camera, screenManager));
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
