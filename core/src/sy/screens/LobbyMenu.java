package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Scaling;
import com.esotericsoftware.minlog.Log;

import java.util.Collection;

import javax.xml.soap.Text;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.connection.ClientHandler;
import sy.connection.NetworkPackageCallbacks;
import sy.connection.ServerHandler;
import sy.core.LobbyLogic;
import sy.core.LobbyLogicClient;
import sy.core.LobbyLogicServer;
import sy.core.Player;
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
    }

    public void rebuildUi() {
        for(Actor actor : getActors())
        {
            actor.remove();
        }

        AliveButton ready;
        Texture joinTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_READY);
        ready = new AliveButton(joinTexture);
        Vector2 btnJoinSize = Scaling.fillX.apply(joinTexture.getWidth(), joinTexture.getHeight(), screenWidth*0.40f,0);
        ready.setSize(btnJoinSize.x, btnJoinSize.y);
        ready.setPosition(screenWidth/2 - ready.getWidth()/2, padding);
        addActorsToStage(ready);

        ready.addListener(() -> {
            String str = "";
            for(Player p : lobbyLogic.getCurrentPlayers().values()) {
                str += "Player " + p.getConnectionId() + "\n";
            }
            Gdx.app.log("PLAYERS", "\n" + str);
        });

        int i = 0;
        for(Player currPlayer : lobbyLogic.getCurrentPlayers().values()) {
            float screenY = screenHeight - 100 - 150 * i;
            TextField field = createTextField(screenY, "Player " + currPlayer.getConnectionId());
            addActorsToStage(field);
            i++;
        }
    }

    private TextField createTextField(float screenY, String text) {
        TextField field = new TextField(text, textfieldSkin);
        field.setSize(screenWidth*0.6f, screenHeight*0.1f);
        field.setPosition(screenWidth/2 - field.getWidth()/2, screenY - field.getHeight()/2);
        return field;
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
        lobbyLogic = new LobbyLogicServer(server, this);
        ((LobbyLogicServer)lobbyLogic).createSelf();
        Gdx.app.log("LOBBY", "Initialized Server");
    }

    public void init(ClientHandler client) {
        lobbyLogic = new LobbyLogicClient(client, this);
        ((LobbyLogicClient)lobbyLogic).sendJoinRequest();
        Gdx.app.log("LOBBY", "Initialized Client");
    }

}




