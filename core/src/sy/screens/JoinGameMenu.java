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

import sy.DialogWindow.HostButton.HostBothEmpty;
import sy.DialogWindow.HostButton.HostBothFilled;
import sy.DialogWindow.HostButton.HostNameEmpty;
import sy.DialogWindow.JoinButton.JoinBothEmpty;
import sy.DialogWindow.JoinButton.JoinIpEmpty;
import sy.DialogWindow.JoinButton.JoinNameEmpty;
import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.connection.ClientHandler;
import sy.connection.NetworkPackageCallbacks;
import sy.connection.ServerHandler;
import sy.rendering.RenderPipeline;
import sy.ui.AliveButton;

public class JoinGameMenu extends AbstractScreen {
    private float screenWidth;
    private float screenHeight;
    private ScreenManager screenManager;
    Sound sound = SYAssetManager.getAsset(AssetDescriptors.buttonSound);
    private Skin textfieldSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    Skin Dialogskin = new Skin(Gdx.files.internal("skin/uiskin.json"));


    public JoinGameMenu(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager) {
        this.screenManager = screenManager;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        textfieldSkin.getFont("default-font").getData().setScale(2.75f);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this); }

    @Override
    public void buildStage() {
        TextField userName;
        TextField userIP;
        TextField tcpPort;
        TextField udpPort;
        AliveButton join;
        AliveButton host;
        AliveButton leave;
        NetworkPackageCallbacks networkPackageCallbacks = new NetworkPackageCallbacks();


        userIP = new TextField("",textfieldSkin);
        userIP.setMessageText("Enter IP");
        userIP.setSize(screenWidth *0.6f, screenHeight*0.1f);
        userIP.setPosition(screenWidth/2 - userIP.getWidth()/2, screenHeight/2 + (userIP.getHeight()*3f));
        addActorsToStage(userIP);

        userName = new TextField("",textfieldSkin);
        userName.setMessageText("Playername");
        userName.setSize(screenWidth *0.6f, screenHeight*0.1f); 
        userName.setPosition(screenWidth/2 - userName.getWidth()/2, screenHeight/2 + (userName.getHeight()*1.5f));
        addActorsToStage(userName);

        tcpPort = new TextField("54555", textfieldSkin);
        tcpPort.setSize(screenWidth*0.6f, screenHeight*0.1f);
        tcpPort.setPosition(screenWidth/2 - tcpPort.getWidth()/2, screenHeight /2f);
        addActorsToStage(tcpPort);

        udpPort = new TextField("54777", textfieldSkin);
        udpPort.setSize(screenWidth*0.6f, screenHeight*0.1f);
        udpPort.setPosition(screenWidth/2 - udpPort.getWidth()/2, screenHeight/2.8f);
        addActorsToStage(udpPort);


        Texture hostTexture = SYAssetManager.getAsset(AssetDescriptors.HOST_GAME);
        host = new AliveButton(hostTexture);
        Vector2 btnHostSize = Scaling.fillX.apply(hostTexture.getWidth(), hostTexture.getHeight(), screenWidth*0.40f,0);
        host.setSize(btnHostSize.x, btnHostSize.y);
        host.setPosition(screenWidth/2+(host.getWidth()/80), screenHeight/20);
        addActorsToStage(host);

        Texture joinTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_JOIN);
        join = new AliveButton(joinTexture);
        Vector2 btnJoinSize = Scaling.fillX.apply(joinTexture.getWidth(), joinTexture.getHeight(), screenWidth*0.40f,0);
        join.setSize(btnJoinSize.x, btnJoinSize.y);
        join.setPosition(screenWidth/2 - join.getWidth(), screenHeight/20);
        addActorsToStage(join);

        Texture leaveTexture = SYAssetManager.getAsset(AssetDescriptors.LEAVE);
        leave = new AliveButton(leaveTexture);
        Vector2 btnLeaveSize = Scaling.fillX.apply(leaveTexture.getWidth(), leaveTexture.getHeight(), screenWidth*0.10f, 0);
        leave.setSize(btnLeaveSize.x, btnLeaveSize.y);
        leave.setPosition(screenWidth-(leave.getWidth()/1.5f), screenHeight - leave.getHeight());
        addActorsToStage(leave);

        host.addListener(() -> {
            if (!userIP.getText().trim().isEmpty() && !userName.getText().trim().isEmpty()){
                HostBothFilled hostBothFilled = new HostBothFilled("ERROR", Dialogskin);
                addActorsToStage(hostBothFilled);
                hostBothFilled.setPosition(screenWidth/2f - hostBothFilled.getWidth() / 2f, screenHeight / 2f - hostBothFilled.getHeight() / 2f);
                hostBothFilled.setSize(200,200);
            }
            else if(userIP.getText().trim().isEmpty() && userName.getText().trim().isEmpty()){
                HostBothEmpty hostBothEmpty = new HostBothEmpty("ERROR", Dialogskin);
                addActorsToStage(hostBothEmpty);
                hostBothEmpty.setPosition(screenWidth/2f - hostBothEmpty.getWidth() / 2f, screenHeight / 2f - hostBothEmpty.getHeight() / 2f);
                hostBothEmpty.setSize(200,200);
            }
            else if (!userIP.getText().trim().isEmpty() && userName.getText().trim().isEmpty()){
                HostNameEmpty hostNameEmpty = new HostNameEmpty("ERROR", Dialogskin);
                addActorsToStage(hostNameEmpty);
                hostNameEmpty.setPosition(screenWidth/2f - hostNameEmpty.getWidth() / 2f, screenHeight / 2f - hostNameEmpty.getHeight() / 2f);
                hostNameEmpty.setSize(200,200);
            }
            else {
                userName.setText(userName.getText());
                ServerHandler server = new ServerHandler(networkPackageCallbacks);
                server.serverStart(Integer.parseInt(tcpPort.getText()), Integer.parseInt(udpPort.getText()));
                sound.play();
                screenManager.showScreen(LobbyMenu.class);
                screenManager.getScreen(LobbyMenu.class).init(server);
                pause();
            }
        });


        join.addListener(() -> {
            //ToDo: check connection worked
            ClientHandler client = new ClientHandler(networkPackageCallbacks);
            if (userIP.getText().trim().isEmpty() && userName.getText().isEmpty()){
                JoinBothEmpty joinBothEmpty = new JoinBothEmpty("ERROR", Dialogskin);
                addActorsToStage(joinBothEmpty);
                joinBothEmpty.setPosition(screenWidth/2f - joinBothEmpty.getWidth() / 2f, screenHeight / 2f - joinBothEmpty.getHeight() / 2f);
                joinBothEmpty.setSize(200,200);
            }
            else if(userIP.getText().trim().isEmpty() && !userName.getText().trim().isEmpty()){
                JoinIpEmpty joinIpEmpty = new JoinIpEmpty("ERROR", Dialogskin);
                addActorsToStage(joinIpEmpty);
                joinIpEmpty.setPosition(screenWidth/2f - joinIpEmpty.getWidth() / 2f, screenHeight / 2f - joinIpEmpty.getHeight() / 2f);
                joinIpEmpty.setSize(200,200);
            }
            else if(!userIP.getText().trim().isEmpty() && userName.getText().trim().isEmpty()){
                JoinNameEmpty joinNameEmpty = new JoinNameEmpty("ERROR", Dialogskin);
                addActorsToStage(joinNameEmpty);
                joinNameEmpty.setPosition(screenWidth/2f - joinNameEmpty.getWidth() / 2f, screenHeight / 2f - joinNameEmpty.getHeight() / 2f);
                joinNameEmpty.setSize(200,200);

            }
            else {
                userName.setText(userName.getText());
                userIP.setText(userIP.getText());
                client.clientStart(userIP.getText(), Integer.parseInt(tcpPort.getText()), Integer.parseInt(udpPort.getText()));
                sound.play();
                screenManager.showScreen(LobbyMenu.class);
                screenManager.getScreen(LobbyMenu.class).init(client);
               pause();
            }
        });


        leave.addListener(() -> {
            sound.play();
            hide();
            screenManager.showScreen(MainMenuScreen.class);
        });
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
    @Override
    public void pause(){

    }

    @Override
    public void dispose(){

    }

}




