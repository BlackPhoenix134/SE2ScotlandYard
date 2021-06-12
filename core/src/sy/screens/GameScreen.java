package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.List;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.assets.ShaderManager;
import sy.connection.ClientHandler;
import sy.connection.NetworkPackageCallbacks;
import sy.connection.ServerHandler;
import sy.core.Gameplay;
import sy.core.GameplayClient;
import sy.core.GameplayServer;
import sy.core.LivingBoard.CritterSpawnerManager;
import sy.core.MapNode;
import sy.core.Player;
import sy.core.PlayerTurnIF;
import sy.core.Tickets.TicketType;
import sy.gameObjects.GameBoardObject;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnObject;
import sy.input.InputHandler;
import sy.input.PanListener;
import sy.input.TouchDownListener;
import sy.input.TouchUpListener;
import sy.input.ZoomListener;
import sy.rendering.RenderPipeline;
import sy.ui.AliveButton;
import sy.ui.FillableImage;

public class GameScreen extends AbstractScreen implements TouchDownListener, TouchUpListener, ZoomListener, PanListener, PlayerTurnIF {
    private final float TICKS = 1f / 60f;
    private float tickAccumulation = 0;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private RenderPipeline renderPipeline;
    private OrthographicCamera camera;
    private InputHandler inputHandler;
    private Vector2 dragValue = new Vector2();
    private Vector2 oldDragValue = new Vector2();
    private boolean moveCamToPawnObject = false;
    private Vector2 camDestinationPosition = new Vector2();
    private float currentScale = 1;
    private float zoomValue = 1;
    private ScreenManager screenManager;
    private GameBoardObject gameBoardObject;
    private CritterSpawnerManager critterSpawnerManager;
    private NodeGraphObject nodeGraphObject;
    private ShaderManager shaderManager;
    private TicketType ticketType;
    private Gameplay gameplay;
    private NetworkPackageCallbacks callbacks;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));

    private World world = new World(new Vector2(0, 0), true);
    private FillableImage dWonTop, dWonBottom, mrxWonTop, mrxWonBottom;

    public GameScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager, ShaderManager shaderManager) {
        this.renderPipeline = renderPipeline;
        this.camera = camera;
        this.screenManager = screenManager;
        this.shaderManager = shaderManager;
        nodeGraphObject = gameObjectManager.create(NodeGraphObject.class);
        gameBoardObject = gameObjectManager.create(GameBoardObject.class);
        Texture gameBoardTexture = SYAssetManager.getAsset(AssetDescriptors.GAME_BOARD);
        gameBoardObject.setTexture(gameBoardTexture);
        critterSpawnerManager = new CritterSpawnerManager(gameObjectManager);
    }


    @Override
    public void buildStage() {
        //I commented this, because it was being created but never was being displayed
        //it was because you needed to add super.render(delta) at the render method of this class
        //if you uncomment the code below it will show the buttons..
        /**
         AliveButton btnMisterX;
         AliveButton btnDetective;

         Texture MisterXTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_MISTERX);
         Texture DetectiveTexture = SYAssetManager.getAsset(AssetDescriptors.BUTTON_DETECTIVE);

         btnMisterX = new AliveButton(MisterXTexture);
         btnDetective = new AliveButton(DetectiveTexture);

         Vector2 btnMisterXsize = Scaling.fillX.apply(MisterXTexture.getWidth(), MisterXTexture.getHeight(), getViewport().getScreenWidth() * 0.5f, 0);
         Vector2 btnDetectivesize = Scaling.fillX.apply(DetectiveTexture.getWidth(), DetectiveTexture.getHeight(), getViewport().getScreenWidth() * 0.5f, 0);

         btnMisterX.setSize(btnMisterXsize.x, btnMisterXsize.y);
         btnDetective.setSize(btnDetectivesize.x, btnDetectivesize.y);

         btnMisterX.setPosition(10, 10);
         btnDetective.setPosition(30, 10);

         btnMisterX.addListener(() -> sound.play());

         btnDetective.addListener(() -> sound.play());

         addActorsToStage(btnDetective, btnMisterX);
         **/
        /*
        ShaderDebugObject obj = gameObjectManager.create(ShaderDebugObject.class);
        obj.setSprite(new Sprite(SYAssetManager.getAsset(AssetDescriptors.BIKE)));
        obj.setShader(shaderManager.loadShader(Gdx.files.internal("passthrough.vert.glsl").path(), Gdx.files.internal("flowmap.frag.glsl").path()));
        */
    }

    private void animateEndImages(Texture topTexture, Texture bottomTexture) {
        Gdx.input.setInputProcessor(this);
        FillableImage top  = new FillableImage(topTexture);
        FillableImage bottom  = new FillableImage(bottomTexture);
        AliveButton  leaveButton = new AliveButton(SYAssetManager.getAsset(AssetDescriptors.LEAVE_GAME));

        top.fillX(Gdx.graphics.getWidth()/2);
        bottom.fillX(Gdx.graphics.getWidth()/2);
        leaveButton.fillX(top.getWidth() * 0.65f);

        leaveButton.setPosition(-leaveButton.getWidth()*2f, Gdx.graphics.getHeight()/2 - leaveButton.getHeight()/2);
        top.setPosition(Gdx.graphics.getWidth()/2 - top.getWidth()/2, Gdx.graphics.getHeight());
        bottom.setPosition(Gdx.graphics.getWidth()/2 - bottom.getWidth()/2, -bottom.getHeight());

        leaveButton.addAction(Actions.sequence(
                Actions.moveTo(Gdx.graphics.getWidth()/2 - leaveButton.getWidth()/2,leaveButton.getY(), 0.5f, Interpolation.circleOut)
        ));

        top.addAction(Actions.sequence(
                Actions.moveTo(top.getX(), Gdx.graphics.getHeight()/2 - top.getHeight()/2, 0.5f, Interpolation.circleOut)
        ));
        bottom.addAction(Actions.sequence(
                Actions.moveTo(bottom.getX(), Gdx.graphics.getHeight()/2 - bottom.getHeight()/2, 0.5f, Interpolation.circleOut)
        ));

        leaveButton.addListener(() -> {
            //TODO change the screen here
            //screenManager.showScreen();
        });
        addActorsToStage(top, bottom, leaveButton);
    }

    public void initialize(ServerHandler handler, NetworkPackageCallbacks callbacks, List<Player> players, Player localPlayer) {
        this.gameplay = new GameplayServer(localPlayer, players, handler, gameObjectManager);
        this.gameplay.setPlayerTurnIF(this);
        initialize(gameplay, callbacks);
    }

    public void initialize(ClientHandler handler, NetworkPackageCallbacks callbacks, List<Player> players, Player localPlayer) {
        this.gameplay = new GameplayClient(localPlayer, players, handler, gameObjectManager);
        this.gameplay.setPlayerTurnIF(this);
        initialize(gameplay, callbacks);
    }

    private void initialize(Gameplay gameplay, NetworkPackageCallbacks callbacks) {
        this.callbacks = callbacks;
        this.gameplay.setPlayerTurnIF(this);
        gameplay.initialize(nodeGraphObject);

        gameplay.addListener(new Gameplay.GamePlayListener() {

            @Override
            public void onDetectiveWin() {
                animateEndImages(SYAssetManager.getAsset(AssetDescriptors.Detectives), SYAssetManager.getAsset(AssetDescriptors.DWon));
            }

            @Override
            public void onMisterXWin() {
                animateEndImages(SYAssetManager.getAsset(AssetDescriptors.MrX), SYAssetManager.getAsset(AssetDescriptors.MWon));
            }
        });
    }

    @Override
    public void render(float delta) {
        delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tickAccumulation += Math.min(delta, 0.25f);
        if (tickAccumulation >= TICKS) {
            tickAccumulation -= TICKS; //takes multi phys misses into account (low fps)
            stepTick(delta);
        }
        stepFastUpdate(delta);
        super.render(delta);
    }

    private void stepTick(float delta) {
        critterSpawnerManager.tick(delta);
        gameObjectManager.update(delta);
        gameObjectManager.postUpdate();
    }

    private void stepFastUpdate(float delta) {
        renderPipeline.begin();
        gameObjectManager.draw(delta, renderPipeline);
        renderPipeline.end();
        updateCam();
        renderPipeline.updateBatchMatrix();
    }


    @Override
    public void show() {
        setUpInputHandler();
    }

    private void setUpInputHandler() {
        this.inputHandler = new InputHandler();
        this.inputHandler.setTouchUpListener(this);
        this.inputHandler.setTouchDownListener(this);
        this.inputHandler.setZoomListener(this);
        this.inputHandler.setPanListener(this);
    }

    @Override
    public void pause() {
        //waiting for usage
    }

    @Override
    public void resume() {
        //waiting for usage
    }

    @Override
    public void hide() {
        //waiting for usage
    }

    private void updateCam() {
        camera.zoom = this.zoomValue;
        float scale = this.zoomValue * 2.0f;
        if (oldDragValue.x != dragValue.x || oldDragValue.y != dragValue.y) {
            camera.position.add(-dragValue.x * scale, dragValue.y * scale, 0);
            oldDragValue.x = dragValue.x;
            oldDragValue.y = dragValue.y;

            camera.position.set(clampCam(camera.position, gameBoardObject.getBoundingBox()));
        }
        if(moveCamToPawnObject)
            moveCamToPosition();

        camera.update();
    }

    private void moveCamToPosition(){
        float tolerance = 25 / this.zoomValue;
        float camSpeedFactor = 25 / this.zoomValue;
        if(camera.position.x + tolerance >= camDestinationPosition.x && camera.position.x - tolerance <= camDestinationPosition.x
                && camera.position.y + tolerance >= camDestinationPosition.y && camera.position.y - tolerance <= camDestinationPosition.y) {
            moveCamToPawnObject = false;
            return;
        }

        float lengthX = Math.abs(camera.position.x - camDestinationPosition.x);
        float lengthY = Math.abs(camera.position.y - camDestinationPosition.y);
        float camSpeedX = 1;
        float camSpeedY = 1;
        if(lengthX > lengthY){
            camSpeedY = lengthY / lengthX;
        } else if(lengthX < lengthY){
            camSpeedX = lengthX / lengthY;
        }

        if(camera.position.x + tolerance < camDestinationPosition.x){
            camera.position.x += camSpeedFactor * camSpeedX;
        } else if(camera.position.x - tolerance > camDestinationPosition.x){
            camera.position.x -= camSpeedFactor * camSpeedX;
        }
        if(camera.position.y + tolerance < camDestinationPosition.y){
            camera.position.y += camSpeedFactor * camSpeedY;
        } else if(camera.position.y - tolerance > camDestinationPosition.y){
            camera.position.y -= camSpeedFactor * camSpeedY;
        }
    }

    private Vector3 clampCam(Vector3 position, Rectangle boundingBox) {
        return new Vector3(clamp(position.x, boundingBox.x, boundingBox.width), clamp(position.y, boundingBox.y, boundingBox.height), position.z);
    }

    private float clamp(float value, float min, float max) {
        if (value < min)
            return min;
        return Math.min(value, max);
    }

    @Override
    public void dispose() {
        renderPipeline.dispose();
        world.dispose();

    }

    @Override
    public void onTouchUp(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("Game", "TOUCH ON " + screenX + ", " + screenY);
        Vector3 vector3 = camera.unproject(new Vector3(screenX, screenY, 0));
        Gdx.app.log("Koordinaten:", "new Vector2(" + vector3.x + "f," + vector3.y + "f);");
        int range = 40;
        ticketType = TicketType.BIKE;
        for (MapNode node : nodeGraphObject.getMapNodes()) {     //soon handled by click handler
            Vector2 pos = node.getPosition();
            if (vector3.x >= pos.x - range && vector3.x <= pos.x + range && vector3.y >= pos.y - range && vector3.y <= pos.y + range) {
                //Gdx.app.log("Indizes:", "current index: " + currentindex + " clicked index: " + i);
                gameplay.movePlayer(node, ticketType);
                break;
            }
        }
    }

    @Override
    public void onTouchDown(int screenX, int screenY, int pointer, int button) {
        currentScale = zoomValue;
    }

    @Override
    public void onZoom(float initialDistance, float distance) {
        float ratio = initialDistance / distance;
        this.zoomValue = this.currentScale * ratio;
    }

    @Override
    public void onPan(float x, float y, float deltaX, float deltaY) {
        dragValue.x = deltaX;
        dragValue.y = deltaY;
    }


    @Override
    public void onPlayerTurnChanged(PawnObject pawnObject) {
        moveCamToPawnObject = true;
        camDestinationPosition = pawnObject.getMapNode().getPosition();
    }
}
