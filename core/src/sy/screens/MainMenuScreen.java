package sy.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import sy.Game;
import sy.input.InputHandler;
import sy.rendering.RenderPipeline;

public class MainMenuScreen extends AbstractScreen {
    private Game game;
    private float screenWidth, screenHeight,tw,th =0;
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
        img = new Sprite(new Texture(Gdx.files.internal("startscreen/StartDevil.png")));
        //img.rotate(90);
        tw = img.getWidth();
        th = img.getHeight();
        img.setBounds( screenWidth / 2 , screenHeight / 2, screenWidth / 6, screenHeight / 10);
        img.setPosition(0,0);

    }

    @Override
    public void buildStage() {

        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if(img.getBoundingRectangle().contains(screenX, screenY)) {
                    screenManager.showScreen(GameScreen.class);
                }
                return true;
            }

        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderPipeline.getDefaultRenderer().begin();
        renderPipeline.getDefaultRenderer().add(img);
        renderPipeline.getDefaultRenderer().end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
