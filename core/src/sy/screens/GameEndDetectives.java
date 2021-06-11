package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.rendering.RenderPipeline;
import sy.ui.AliveButton;

public class GameEndDetectives extends AbstractScreen {
    private float screenWidth;
    private float screenHeight;
    private ScreenManager screenManager;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));
    Image detec = new Image(SYAssetManager.getAsset(AssetDescriptors.Detectives));
    Image dWon = new Image(SYAssetManager.getAsset(AssetDescriptors.DWon));
    Image backg = new Image (SYAssetManager.getAsset(AssetDescriptors.BACK_G));
    Sound dWonSound = Gdx.audio.newSound(Gdx.files.internal("EndScreen/DetectivesWin.wav"));

    public GameEndDetectives(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager) {
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
        AliveButton leaveGame;
        Texture leaveGameTex = SYAssetManager.getAsset(AssetDescriptors.LEAVE_GAME);
        leaveGame = new AliveButton(leaveGameTex);
        Vector2 btnStartGameSize = Scaling.fillX.apply(leaveGameTex.getWidth(), leaveGameTex.getHeight(), screenWidth * 0.50f, 0);
        leaveGame.setSize(btnStartGameSize.x, btnStartGameSize.y);
        leaveGame.setPosition( screenWidth/2 - leaveGame.getWidth()/2, screenHeight/2-leaveGame.getHeight()/2);


        leaveGame.addListener(new AliveButton.AliveButtonListener() {
            @Override
            public void onClick() throws Exception {
                sound.play();
                screenManager.showScreen(MainMenuScreen.class);
                pause();
            }
        });
        dWonSound.play();
        backg.setSize(2028,1080);
        addActorsToStage(backg);
        addActorsToStage(detec);
        addActorsToStage(dWon);
        addActorsToStage(leaveGame);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render(delta); //this render the stage, which is responsible for the screen transitions
    }
}
