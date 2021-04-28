package sy.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.input.InputHandler;
import sy.rendering.RenderPipeline;

public class MainMenuScreen extends AbstractScreen {
    private float screenWidth, screenHeight;
    private Sprite btnStartGame;
    private Sprite btnOptions;
    private Sprite btnExitGame;
    private RenderPipeline renderPipeline;
    private OrthographicCamera camera;
    private ScreenManager screenManager;
    private InputHandler inputHandler;
    private SpriteBatch batch = new SpriteBatch();




    public MainMenuScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager, InputHandler inputHandler) {
        this.renderPipeline = renderPipeline;
        this.camera = camera;
        this.screenManager = screenManager;
        this.inputHandler = inputHandler;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        btnStartGame = new Sprite(SYAssetManager.getAssetManager().get(AssetDescriptors.BUTTON_DEVIL));
        btnOptions = new Sprite (SYAssetManager.getAssetManager().get(AssetDescriptors.BUTTON_OPTIONS));
        btnExitGame = new Sprite(SYAssetManager.getAssetManager().get(AssetDescriptors.BUTTON_EXIT));
        btnStartGame.rotate(90);
        btnOptions.rotate(90);
        btnExitGame.rotate(90);
        btnStartGame.setBounds( screenWidth / 2 , screenHeight / 2, screenWidth / 4, screenHeight / 5);
        btnOptions.setBounds( screenWidth / 2 , screenHeight / 2, screenWidth / 4, screenHeight / 5);
    }

    @Override
    public void buildStage() {


        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if(btnStartGame.getBoundingRectangle().contains(screenX, screenY)) {
                    System.out.println();
                    screenManager.showScreen(GameScreen.class);
                }
                return true;
            }

        });


    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        btnStartGame.draw(batch);
        btnOptions.draw(batch);
        btnExitGame.draw(batch);
        batch.end();

    }


    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

}
