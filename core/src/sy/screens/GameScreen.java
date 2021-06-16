package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.List;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.assets.ShaderManager;
import sy.connection.ClientHandler;
import sy.connection.NetworkPackageCallbacks;
import sy.connection.ServerHandler;
import sy.core.CameraData;
import sy.core.Clickable;
import sy.core.Consumer;
import sy.core.Gameplay;
import sy.core.GameplayClient;
import sy.core.GameplayServer;
import sy.core.LivingBoard.CritterSpawnerManager;
import sy.core.MapNode;
import sy.core.clickHandling.ObjectClickHandler;
import sy.core.Physics.Area2D;
import sy.core.Physics.Rectangle;
import sy.core.Player;
import sy.core.PlayerTurnIF;
import sy.core.Tickets.TicketType;
import sy.gameObjects.GameBoardObject;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnObject;
import sy.input.prio.InputEvent;
import sy.input.prio.InputEventType;
import sy.input.prio.InputHandler;
import sy.rendering.RenderPipeline;
import sy.ui.AliveButton;
import sy.ui.FillableImage;
import sy.gameObjects.InGameUI;
import sy.ui.TicketSelectDialog;

public class GameScreen extends AbstractScreen implements PlayerTurnIF {
    private final float TICKS = 1f / 60f;
    private float tickAccumulation = 0;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private RenderPipeline renderPipeline;
    private OrthographicCamera camera;
    private InputHandler inputHandler;
    private boolean moveCamToPawnObject = false;
    private Vector2 camDestinationPosition = new Vector2();
    private ScreenManager screenManager;
    private GameBoardObject gameBoardObject;
    private CritterSpawnerManager critterSpawnerManager;
    private NodeGraphObject nodeGraphObject;
    private ShaderManager shaderManager;
    private Gameplay gameplay;
    private NetworkPackageCallbacks callbacks;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));
    private Skin uiSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    private CameraData cameraData;
    private ObjectClickHandler objectClickHandler;
    private InGameUI ingameUI;

    private World world = new World(new Vector2(0, 0), true);
    private FillableImage dWonTop, dWonBottom, mrxWonTop, mrxWonBottom;

    public GameScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager, ShaderManager shaderManager) {
        this.renderPipeline = renderPipeline;
        this.camera = camera;
        this.screenManager = screenManager;
        this.shaderManager = shaderManager;
        this.cameraData = new CameraData(camera);
        inputHandler = new InputHandler();
        objectClickHandler = new ObjectClickHandler(cameraData, inputHandler);
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
    //                          that's a beauty
    private void initialize(Gameplay gameplay, NetworkPackageCallbacks callbacks) {
        this.callbacks = callbacks;
        this.gameplay.setPlayerTurnIF(this); //that's a beauty
        gameplay.initialize(nodeGraphObject);
        ingameUI = gameObjectManager.create(InGameUI.class);
        ingameUI.initialize(gameplay, objectClickHandler);

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
        cameraData.update();
        renderPipeline.updateBatchMatrix();
    }

    @Override
    public void show() {
        setupInputhandler();
        setupInputEvents();
        setupGameObjectEvents();
    }

    private void setupInputhandler() {
        Gdx.input.setInputProcessor(inputHandler.getInputMultiplexer());
        inputHandler.unsubscribeAll();
    }

    private void setupInputEvents() {
        this.inputHandler.addListener(InputEventType.TOUCH_DOWN, 100, inputEvent -> {
            Vector3 worldPos = cameraData.getOrthographicCamera().unproject(new Vector3(inputEvent.getX1(), inputEvent.getX2(), 0f));
        });
        this.inputHandler.addListener(InputEventType.TOUCH_DOWN, 100,
                inputEvent -> cameraData.setCurrentScale(cameraData.getZoomValue()));
        this.inputHandler.addListener(InputEventType.ZOOM, 100, inputEvent -> {
            float ratio = inputEvent.getX1() / inputEvent.getX2();
            cameraData.setZoomValue(cameraData.getCurrentScale() * ratio);
        });
        this.inputHandler.addListener( InputEventType.PAN, 100, inputEvent -> cameraData.getDragValue().set(inputEvent.getX3(), inputEvent.getX4()));
    }
    private void setupGameObjectEvents() {
        objectClickHandler.subscribeEvents();
        for(MapNode mapNode : nodeGraphObject.getMapNodes()) {
            objectClickHandler.addTouchUpClickable(new Clickable() {
                @Override
                public void onClicked(InputEvent inputEvent) {
                    inputEvent.setConsumed(true);
                    onMapNodeClicked(mapNode);
                }

                @Override
                public Vector2 getPosition() {
                    return mapNode.getPosition();
                }

                @Override
                public Area2D getArea2D() {
                    Vector2 pos = getPosition();
                    return new Rectangle(pos.x - 20, pos.y - 20, 40, 40);
                }
            }, 10, false);
        }
    }

    private void onMapNodeClicked(MapNode mapNode) {
        //ToDo: get correct ticket type options here


        List<TicketType> options = new ArrayList<TicketType>() {{
            add(TicketType.DRAGON);
            add(TicketType.DOUBLETURN_TICKET);
            add(TicketType.BIKE);
            add(TicketType.BLACK_TICKET);
            add(TicketType.HORSE);
        }};
        //List<TicketType> options = gameplay.getAllowedMoves(gameplay.getLocalPlayerPawn(), mapNode);
        openMoveTicketDialog(mapNode, options);
    }

    private void openMoveTicketDialog(MapNode mapNode, List<TicketType> options) {
        new TicketSelectDialog(gameObjectManager, objectClickHandler, options, ticketType -> {
            if (ticketType == TicketType.DOUBLETURN_TICKET) {
                //gameplay.allowSecondTurn();
                options.remove(TicketType.DOUBLETURN_TICKET);
                openMoveTicketDialog(mapNode, options);
            } else if(ticketType != null) {
               // gameplay.movePlayer(mapNode, ticketType);
            }
        });
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
/*
    private void updateCam() {
        camera.zoom = this.cameraData.getZoomValue();
        float scale = this.cameraData.getZoomValue() * 2.0f;
        if (oldDragValue.x != dragValue.x || oldDragValue.y != dragValue.y) {
            camera.position.add(-dragValue.x * scale, dragValue.y * scale, 0);
            oldDragValue.x = dragValue.x;
            oldDragValue.y = dragValue.y;

            //camera.position.set(clampCam(camera.position, new Rectangle(-500, -500, 500, 500)));
        }
        if(moveCamToPawnObject)
            moveCamToPosition();

        camera.update();
    }

    private void moveCamToPosition(){
        float tolerance = 25 / this.cameraData.getZoomValue();
        float camSpeedFactor = 25 / this.cameraData.getZoomValue();
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
    }*/

    private Vector3 clampCam(Vector3 position, Rectangle boundingBox) {
        return new Vector3(clamp(position.x, boundingBox.getX(), boundingBox.getWidth()), clamp(position.y, boundingBox.getY(), boundingBox.getHeight()), position.z);
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
    public void onPlayerTurnChanged(PawnObject pawnObject) {
        moveCamToPawnObject = true;
        camDestinationPosition = pawnObject.getMapNode().getPosition();
    }
}
