package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Scaling;
import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.connection.ClientHandler;
import sy.connection.NetworkPackageCallbacks;
import sy.connection.ServerHandler;
import sy.rendering.RenderPipeline;
import sy.ui.AliveButton;



public class LobbyMenu extends AbstractScreen {
    private float screenWidth;
    private float screenHeight;
    private ScreenManager screenManager;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));
    private Skin textfieldSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    float padding = screenHeight * 0.05f;


    public LobbyMenu(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager) {
        this.screenManager = screenManager;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        textfieldSkin.getFont("default-font").getData().setScale(2.75f);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

   @Override
    public void buildStage() {
        TextField hostIP;
        TextField P1, P2, P3, P4, P5;
        AliveButton start;
        NetworkPackageCallbacks networkPackageCallbacks = new NetworkPackageCallbacks();
        ClientHandler client = new ClientHandler(networkPackageCallbacks);

        Texture joinTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_READY);
        start = new AliveButton(joinTexture);
        Vector2 btnJoinSize = Scaling.fillX.apply(joinTexture.getWidth(), joinTexture.getHeight(), screenWidth*0.40f,0);
        start.setSize(btnJoinSize.x, btnJoinSize.y);
        start.setPosition(screenWidth/2 - start.getWidth()/2, padding);
        addActorsToStage(start);

        P1 = new TextField("", textfieldSkin);
        P1.setSize(screenWidth*0.6f, screenHeight*0.1f);
        P1.setPosition(screenWidth/2 - P1.getWidth()/2, screenHeight /2f);
        addActorsToStage(P1);




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




