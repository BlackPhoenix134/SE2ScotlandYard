package sy.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import sy.Game;
import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.input.InputHandler;
import sy.rendering.RenderPipeline;

public class MainMenuScreen extends AbstractScreen {
    private float screenWidth, screenHeight;
    private Sprite img;
    private RenderPipeline renderPipeline;
    private OrthographicCamera camera;
    private ScreenManager screenManager;
    private InputHandler inputHandler;

    public MainMenuScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager, InputHandler inputHandler) {
        this.renderPipeline = renderPipeline;
        this.camera = camera;
        this.screenManager = screenManager;
        this.inputHandler = inputHandler;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        img = new Sprite(SYAssetManager.getAssetManager().get(AssetDescriptors.BUTTON_DEVIL));
        img.rotate(90);
        img.setBounds( screenWidth / 2 , screenHeight / 2, screenWidth / 4, screenHeight / 5);
    }

    @Override
    public void buildStage() {
        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                //if(img.getBoundingRectangle().contains(screenX, screenY)) {
                    screenManager.showScreen(GameScreen.class);
                //}
                return true;
            }

        });
    }

    SpriteBatch batch = new SpriteBatch();

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        img.draw(batch);
        batch.end();

    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
