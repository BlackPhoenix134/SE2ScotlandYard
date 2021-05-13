package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.rendering.RenderPipeline;
import sy.ui.AliveButton;

public class LobbyMenu extends AbstractScreen{
    private float              screenWidth, screenHeight;
    private AliveButton        btnJoinGame;
    private RenderPipeline renderPipeline;
    private OrthographicCamera camera;
    private ScreenManager      screenManager;
    private SpriteBatch batch = new SpriteBatch();
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));


    public LobbyMenu(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager) {
        this.renderPipeline = renderPipeline;
        this.camera = camera;
        this.screenManager = screenManager;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void buildStage() {
        Gdx.input.setInputProcessor(this);
        float padding = screenHeight * 0.05f;

        Texture joinGameTexture = SYAssetManager.getAssetManager().get(AssetDescriptors.BUTTON_GAMEJOIN);
         btnJoinGame = new AliveButton(joinGameTexture);
        Vector2 btnJoinGameSize    = Scaling.fillX.apply(joinGameTexture.getWidth(), joinGameTexture.getHeight(), screenWidth * 0.30f, 0);
        btnJoinGame.setSize(btnJoinGameSize.x, btnJoinGameSize.y);
        btnJoinGame.setPosition(screenWidth/2 - btnJoinGame.getWidth()/2, padding);

        addActorsToStage(btnJoinGame);


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




