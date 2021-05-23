package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.rendering.RenderPipeline;
import sy.ui.AliveButton;

public class MainMenuScreen extends AbstractScreen {
    private float screenWidth;
    private float screenHeight;
    private ScreenManager screenManager;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));

    public MainMenuScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager) {
        this.screenManager = screenManager;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

   @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void buildStage() {
        AliveButton btnStartGame;
        AliveButton btnExitGame;
        AliveButton btnJoinGame;
        float padding = screenHeight * 0.05f;

        Texture startGameTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_DEVIL);
        Texture exitGameTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_EXIT);
        Texture joinGameTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_JOIN);

        btnStartGame = new AliveButton(startGameTexture);
        btnExitGame  = new AliveButton(exitGameTexture);
        btnJoinGame  = new AliveButton(joinGameTexture);

        Vector2 btnStartGameSize = Scaling.fillX.apply(startGameTexture.getWidth(), startGameTexture.getHeight(), screenWidth * 0.30f, 0);
        Vector2 btnJoinGameSize = Scaling.fillX.apply(joinGameTexture.getWidth(), joinGameTexture.getHeight(), screenWidth * 0.30f, 0);
        Vector2 btnExitSize      = Scaling.fillX.apply(exitGameTexture.getWidth(), exitGameTexture.getHeight(), screenWidth * 0.30f, 0);


        btnStartGame.setSize(btnStartGameSize.x, btnStartGameSize.y);
        btnJoinGame.setSize (btnJoinGameSize.x, btnJoinGameSize.y);
        btnExitGame.setSize(btnExitSize.x, btnExitSize.y);

        btnStartGame.setPosition( screenWidth/2 - btnStartGame.getWidth()/2, screenHeight - padding - btnStartGame.getHeight());
        btnJoinGame.setPosition( screenWidth/2 - btnJoinGame.getWidth()/2, screenHeight  * 0.5f - btnJoinGame.getHeight() * 0.5f);
        btnExitGame.setPosition(screenWidth/2 - btnExitGame.getWidth()/2, padding);

        btnStartGame.addListener(new AliveButton.AliveButtonListener() {
            @Override
            public void onClick() {
                sound.play();
                screenManager.showScreen(HostGameMenu.class);
            }
        });

        btnJoinGame.addListener(new AliveButton.AliveButtonListener(){
            @Override
                    public void onClick(){
                sound.play();
                screenManager.showScreen(LobbyMenu.class);
            }

        });

        addActorsToStage(btnStartGame, btnJoinGame, btnExitGame);

        btnExitGame.addListener(new AliveButton.AliveButtonListener(){
            @Override
            public void onClick(){
                sound.play();
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
