package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.input.InputHandler;
import sy.rendering.RenderPipeline;
import sy.ui.AliveButton;

public class MainMenuScreen extends AbstractScreen {
    private float              screenWidth, screenHeight;
    private AliveButton        btnStartGame;
    private AliveButton        btnOptions;
    private AliveButton        btnExitGame;
    private RenderPipeline     renderPipeline;
    private OrthographicCamera camera;
    private ScreenManager      screenManager;
    private InputHandler       inputHandler;
    private SpriteBatch        batch = new SpriteBatch();

    public MainMenuScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager, InputHandler inputHandler) {
        this.renderPipeline = renderPipeline;
        this.camera = camera;
        this.screenManager = screenManager;
        this.inputHandler = inputHandler;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void buildStage() {
        Gdx.input.setInputProcessor(this);
        float padding = screenHeight * 0.05f;

        Texture startGameTexture = SYAssetManager.getAssetManager().get(AssetDescriptors.BUTTON_DEVIL);
        Texture optionsTexture = SYAssetManager.getAssetManager().get(AssetDescriptors.BUTTON_OPTIONS);
        Texture exitGameTexture = SYAssetManager.getAssetManager().get(AssetDescriptors.BUTTON_EXIT);

        btnStartGame = new AliveButton(startGameTexture);
        btnOptions   = new AliveButton(optionsTexture);
        btnExitGame  = new AliveButton(exitGameTexture);

        Vector2 btnStartGameSize = Scaling.fillX.apply(startGameTexture.getWidth(), startGameTexture.getHeight(), screenWidth * 0.30f, 0);
        Vector2 btnOptionsSize   = Scaling.fillX.apply(optionsTexture.getWidth(), optionsTexture.getHeight(), screenWidth * 0.30f, 0);
        Vector2 btnExitSize      = Scaling.fillX.apply(exitGameTexture.getWidth(), exitGameTexture.getHeight(), screenWidth * 0.30f, 0);

        btnStartGame.setSize(btnStartGameSize.x, btnStartGameSize.y);
        btnOptions.setSize(btnOptionsSize.x, btnOptionsSize.y);
        btnExitGame.setSize(btnExitSize.x, btnExitSize.y);

        btnStartGame.setPosition( screenWidth/2 - btnStartGame.getWidth()/2, screenHeight - padding - btnStartGame.getHeight());
        btnOptions.setPosition( screenWidth/2 - btnOptions.getWidth()/2, screenHeight  * 0.5f - btnOptions.getHeight() * 0.5f);
        btnExitGame.setPosition(screenWidth/2 - btnExitGame.getWidth()/2, padding);

        btnStartGame.addListener(new AliveButton.AliveButtonListener() {
            @Override
            public void onClick() {
                screenManager.showScreen(GameScreen.class);
            }
        });

        btnOptions.addListener(new AliveButton.AliveButtonListener(){
            @Override
                    public void onClick(){
                screenManager.showScreen(OptionsScreen.class);
            }

        });

        addActorsToStage(btnStartGame, btnOptions, btnExitGame);

        btnExitGame.addListener(new AliveButton.AliveButtonListener(){
            @Override
            public void onClick(){
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render(delta); //this render the stage, which is responsible for the screen transitions
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
