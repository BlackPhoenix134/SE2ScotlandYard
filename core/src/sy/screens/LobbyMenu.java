package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Scaling;


import java.util.Arrays;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.connection.ClientHandler;
import sy.connection.ServerHandler;
import sy.core.LobbyLogic;
import sy.core.LobbyLogicClient;
import sy.core.LobbyLogicServer;
import sy.core.LobbyPlayer;
import sy.platforms.CameraPeripheral;
import sy.rendering.RenderPipeline;
import sy.ui.AliveButton;



public class LobbyMenu extends AbstractScreen {
    private float screenWidth;
    private float screenHeight;
    private ScreenManager screenManager;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));
    private Skin textfieldSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    float padding = screenHeight * 0.05f;
    private LobbyLogic lobbyLogic;
    private CameraPeripheral cameraPeripheral;


    public LobbyMenu(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager, CameraPeripheral cameraPeripheral) {
        this.screenManager = screenManager;
        this.cameraPeripheral = cameraPeripheral;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        textfieldSkin.getFont("default-font").getData().setScale(2.75f);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);

        cameraPeripheral.setOnCameraResult((imageData) -> {
            lobbyLogic.setCustomTexture(imageData);
        });
    }


   @Override
    public void buildStage() {
   }

    public void rebuildUi() {
        AliveButton leave;
        Texture leaveTexture = SYAssetManager.getAsset(AssetDescriptors.LEAVE);
        leave = new AliveButton(leaveTexture);
        Vector2 btnLeaveSize = Scaling.fillX.apply(leaveTexture.getWidth(), leaveTexture.getHeight(), screenWidth * 0.10f, 0);
        leave.setSize(btnLeaveSize.x, btnLeaveSize.y);
        leave.setPosition(screenWidth - (leave.getWidth() / 1.5f), screenHeight - leave.getHeight());
        addActorsToStage(leave);

        leave.addListener(() -> {
            sound.play();
            hide();
            screenManager.showScreen(JoinGameMenu.class);
        });


        for (Actor actor : getActors()) {
            actor.remove();
        }

        Texture readyTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_READY);
        AliveButton btnReady = new AliveButton(readyTexture);
        Vector2 btnSize = Scaling.fillX.apply(readyTexture.getWidth(), readyTexture.getHeight(), screenWidth * 0.40f, 0);
        btnReady.setSize(btnSize.x, btnSize.y);
        btnReady.setPosition(screenWidth / 2 - btnReady.getWidth() / 2, padding + 100);
        addActorsToStage(btnReady);
        btnReady.addListener(() -> {
            sound.play();
            lobbyLogic.readyUp();
            pause();
        });

        Texture photoTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_PHOTO);
        AliveButton photo = new AliveButton(photoTexture);
        Vector2 photoSize = Scaling.fillX.apply(photoTexture.getWidth(), photoTexture.getHeight(), screenWidth * 0.15f, 0);
        photo.setSize(photoSize.x, photoSize.y);
        photo.setPosition(screenWidth - photo.getWidth()*6.8f, screenHeight - photo.getHeight()-20);
        addActorsToStage(photo);

        photo.addListener(() -> {
            sound.play();
            cameraPeripheral.startCamera();
            pause();
        });


        if (lobbyLogic instanceof LobbyLogicServer && lobbyLogic.allReady()) {
            Texture startTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_START);
            AliveButton btnStartGame = new AliveButton(startTexture);
            Vector2 btnSize2 = Scaling.fillX.apply(readyTexture.getWidth(), readyTexture.getHeight(), screenWidth * 0.40f, 0);
            btnStartGame.setSize(btnSize2.x, btnSize2.y);
            btnStartGame.setPosition(screenWidth / 2 - btnStartGame.getWidth() / 2, padding - 100);
            addActorsToStage(btnStartGame);
            btnStartGame.addListener(() -> {
                sound.play();
                ((LobbyLogicServer) lobbyLogic).startGame();
                pause();
            });
        }


        int i = 0;
        for (LobbyPlayer currPlayer : lobbyLogic.getCurrLobbyPlayers().values()) {
            float screenY = screenHeight - 100 - 150 * i;
            String text = "Player " + currPlayer.getConnectionId();
            TextField field = createTextField(screenY, text);
            AliveButton visualReadyButton = createVisualReady(screenY);
            addActorsToStage(field);
            visualReadyButton.remove();

            if (currPlayer.isReady()) {
                text += "-> Ready";
                addActorsToStage(visualReadyButton);
            }
            i++;
        }
    }


        private TextField createTextField ( float screenY, String text){
            TextField field = new TextField(text, textfieldSkin);
            field.setDisabled(true);
            field.setSize(screenWidth * 0.6f, screenHeight * 0.1f);
            field.setPosition(screenWidth / 2 - field.getWidth() / 2, screenY - field.getHeight() / 2);
            return field;
        }

        private AliveButton createVisualReady(float screenY){
        Texture visualReady = SYAssetManager.getAsset(AssetDescriptors.VISUAL_READY);
        Vector2 visualSize = Scaling.fillX.apply(visualReady.getWidth(), visualReady.getHeight(), screenWidth * 0.10f, 0);
        AliveButton visualReadyButton = new AliveButton(visualReady);
        visualReadyButton.setSize(visualSize.x, visualSize.y);
        visualReadyButton.setPosition(screenWidth - visualReadyButton.getWidth()*2, screenY - visualReadyButton.getHeight() / 2);

        return visualReadyButton;
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


    public void init(ServerHandler server) {
        lobbyLogic = new LobbyLogicServer(server, this, screenManager);
        ((LobbyLogicServer)lobbyLogic).createSelf();
        Gdx.app.log("LOBBY", "Initialized Server");
    }

    public void init(ClientHandler client) {
        lobbyLogic = new LobbyLogicClient(client, this, screenManager);
        ((LobbyLogicClient)lobbyLogic).sendJoinRequest();
        Gdx.app.log("LOBBY", "Initialized Client");
    }

}





