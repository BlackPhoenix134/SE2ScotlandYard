package sy.screens;

import com.badlogic.gdx.Gdx;
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
        TextField P1, P2, P3, P4, P5, P6;
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
        P1.setPosition(screenWidth/2 - P1.getWidth()/2, screenHeight-P1.getHeight()-80);
        addActorsToStage(P1);

       P2 = new TextField("", textfieldSkin);
       P2.setSize(screenWidth*0.6f, screenHeight*0.1f);
       P2.setPosition(screenWidth/2 - P2.getWidth()/2, screenHeight-P2.getHeight()-200);
       addActorsToStage(P2);

       P3 = new TextField("", textfieldSkin);
       P3.setSize(screenWidth*0.6f, screenHeight*0.1f);
       P3.setPosition(screenWidth/2 - P3.getWidth()/2, screenHeight-P3.getHeight()-320);
       addActorsToStage(P3);

       P4 = new TextField("", textfieldSkin);
       P4.setSize(screenWidth*0.6f, screenHeight*0.1f);
       P4.setPosition(screenWidth/2 - P4.getWidth()/2, screenHeight/2-8);
       addActorsToStage(P4);

       P5 = new TextField("", textfieldSkin);
       P5.setSize(screenWidth*0.6f, screenHeight*0.1f);
       P5.setPosition(screenWidth/2 - P5.getWidth()/2, screenHeight/2-P4.getHeight()-20);
       addActorsToStage(P5);

       P6 = new TextField("", textfieldSkin);
       P6.setSize(screenWidth*0.6f, screenHeight*0.1f);
       P6.setPosition(screenWidth/2 - P6.getWidth()/2, screenHeight/2-(P4.getHeight()*2)-35);
       addActorsToStage(P6);

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




