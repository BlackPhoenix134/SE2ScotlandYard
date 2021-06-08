package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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

public class MainMenuScreen extends AbstractScreen{
    private float screenWidth;
    private float screenHeight;
    private ScreenManager screenManager;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));
    Music background = Gdx.audio.newMusic(Gdx.files.internal("sounds/startMusic.mp3"));

    SpriteBatch batch;
    Texture img;




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
        background.setVolume(0.01f);
        background.setLooping(true);
        background.play();
        AliveButton btnStartGame;
        float padding = screenHeight * 0.05f;


        Texture startGameTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_DEVIL);
        btnStartGame = new AliveButton(startGameTexture);
        Vector2 btnStartGameSize = Scaling.fillX.apply(startGameTexture.getWidth(), startGameTexture.getHeight(), screenWidth * 0.50f, 0);
        btnStartGame.setSize(btnStartGameSize.x, btnStartGameSize.y);
        btnStartGame.setPosition( screenWidth/2 - btnStartGame.getWidth()/2, screenHeight*0.5f-btnStartGame.getHeight()*0.5f);

        batch = new SpriteBatch();
        img = new Texture("background/337.jpg");



        btnStartGame.addListener(new AliveButton.AliveButtonListener() {
            @Override
            public void onClick() {
                sound.play();
                screenManager.showScreen(JoinGameMenu.class);
                background.stop();
                pause();
            }
        });
        addActorsToStage(btnStartGame);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        super.render(delta); //this render the stage, which is responsible for the screen transitions
        //batch.draw(img, 0,0,2028,1080);

        batch.end();
    }


}
