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
        AliveButton btnJoin;
        TextField playerName;
        TextField hostIP;

        NetworkPackageCallbacks networkPackageCallbacks = new NetworkPackageCallbacks();
        ClientHandler client = new ClientHandler(networkPackageCallbacks);

        hostIP = new TextField("",textfieldSkin);
        hostIP.setMessageText("Server IP");
        hostIP.setSize(screenWidth *0.6f, screenHeight*0.1f);
        hostIP.setPosition(screenWidth/2 - hostIP.getWidth()/2, screenHeight/2 + (hostIP.getHeight()*2));
        addActorsToStage(hostIP);

        playerName = new TextField("",textfieldSkin);
        playerName.setMessageText("Playername");
        playerName.setSize(screenWidth *0.6f, screenHeight*0.1f);
        playerName.setPosition(screenWidth/2 - playerName.getWidth()/2, screenHeight/2);
        addActorsToStage(playerName);



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
                screenManager.showScreen(GameScreen.class);
                GameScreen.initialize(client, networkPackageCallbacks);
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




