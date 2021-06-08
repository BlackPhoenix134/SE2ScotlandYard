package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import sy.rendering.RenderPipeline;

public class GameEndMrX extends AbstractScreen {
    private float screenWidth;
    private float screenHeight;
    private ScreenManager screenManager;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));

    SpriteBatch batch;
    Texture img1, img2;

    /*Texture leaveGame = SYAssetManager.getAsset(AssetDescriptors.LEAVE_GAME);
    Texture dWon = SYAssetManager.getAsset(AssetDescriptors.DWon);
    Texture det = SYAssetManager.getAsset(AssetDescriptors.Detectives);
    Texture xWon = SYAssetManager.getAsset(AssetDescriptors.MWon);
    Texture mrX = SYAssetManager.getAsset(AssetDescriptors.MrX);*/



    

    public GameEndMrX(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager) {
        this.screenManager = screenManager;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }
    @Override
    public void buildStage() {

    }
}
