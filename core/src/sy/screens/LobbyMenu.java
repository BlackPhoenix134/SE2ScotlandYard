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

public class LobbyMenu extends AbstractScreen {
    private float screenWidth, screenHeight;
    private AliveButton btnJoin;
    private RenderPipeline renderPipeline;
    private OrthographicCamera camera;
    private ScreenManager screenManager;
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

        Texture joinTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_GAMEJOIN);
        btnJoin = new AliveButton(joinTexture);
        Vector2 btnJoinSize = Scaling.fillX.apply(joinTexture.getWidth(), joinTexture.getHeight(), screenWidth*0.30f,0);
        btnJoin.setSize(btnJoinSize.x, btnJoinSize.y);
        btnJoin.setPosition(screenWidth/2 - btnJoin.getWidth()/2, padding);

        btnJoin.addListener(new AliveButton.AliveButtonListener() {
            @Override
            public void onClick() {
                sound.play();
                screenManager.showScreen(MainMenuScreen.class);
            }
        });

        addActorsToStage(btnJoin);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render(delta);

    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);

    }
}




