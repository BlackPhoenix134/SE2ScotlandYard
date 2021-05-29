package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Scaling;

import java.util.List;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.connection.ClientHandler;
import sy.connection.NetworkPackageCallbacks;
import sy.connection.ServerHandler;
import sy.connection.packages.MovePlayerObject;
import sy.core.Extensions.Collections;
import sy.core.Gameplay;
import sy.core.GameplayClient;
import sy.core.GameplayServer;
import sy.core.LivingBoard.CritterSpawnerManager;
import sy.core.MapNode;
import sy.connection.packages.RemoveTicket;
import sy.core.Player;
import sy.core.Tickets.DetectiveTickets;
import sy.core.Tickets.TicketType;
import sy.connection.packages.UpdateTickets;
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

public class GameScreen extends AbstractScreen implements TouchDownListener, TouchUpListener, ZoomListener, PanListener {
    private final float TICKS = 1f / 60f;
    private float tickAccumulation = 0;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private RenderPipeline renderPipeline;
    private OrthographicCamera camera;
    private InputHandler inputHandler;
    private Vector2 dragValue = new Vector2();
    private Vector2 oldDragValue = new Vector2();
    private float currentScale = 1;
    private float zoomValue = 1;
    private ScreenManager screenManager;
    private PawnObject localPlayerPawn;
    private GameBoardObject gameBoardObject;
    private CritterSpawnerManager critterSpawnerManager;
    private NodeGraphObject nodeGraphObject;
    private TicketType ticketType;
    private Gameplay gameplay;
    private List<PawnObject> pawnPlayerObjects;
    private NetworkPackageCallbacks callbacks;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.mp3"));

    private World world = new World(new Vector2(0, 0), true);

    public GameScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager) {
        this.renderPipeline = renderPipeline;
        this.camera = camera;
        this.screenManager = screenManager;
        nodeGraphObject = gameObjectManager.create(NodeGraphObject.class);
        gameBoardObject = gameObjectManager.create(GameBoardObject.class);
        Texture gameBoardTexture = SYAssetManager.getAsset(AssetDescriptors.GAME_BOARD);
        gameBoardObject.setTexture(gameBoardTexture);
        critterSpawnerManager = new CritterSpawnerManager(gameObjectManager);

    }


    @Override
    public void buildStage() {

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

        btnMisterX.addListener(new AliveButton.AliveButtonListener() {
            @Override
            public void onClick() {
                sound.play();

            }
        });
        addActorsToStage(btnDetective);

        btnDetective.addListener(new AliveButton.AliveButtonListener() {
            @Override
            public void onClick() {
                sound.play();
            }
        });

        addActorsToStage(btnMisterX);

    }

    public void initialize(ServerHandler handler, NetworkPackageCallbacks callbacks, List<Player> players, Player localPlayer) {
        this.gameplay = new GameplayServer(localPlayer, players, handler, gameObjectManager);
        this.callbacks = callbacks;
        gameplay.initialize(nodeGraphObject);
    }

    public void initialize(ClientHandler handler, NetworkPackageCallbacks callbacks, List<Player> players, Player localPlayer) {
        this.gameplay = new GameplayClient(localPlayer, players, handler, gameObjectManager);
        this.callbacks = callbacks;
        gameplay.initialize(nodeGraphObject);
    }


    @Override
    public void render(float delta) {
        delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tickAccumulation += Math.min(delta, 0.25f);
        if (tickAccumulation >= TICKS) {
            tickAccumulation -= TICKS; //takes multi phys misses into account (low fps)
            stepTick(delta);
        }
        stepFastUpdate(delta);
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
        camera.update();
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

        for (MapNode node : nodeGraphObject.getMapNodes()) {     //soon handled by click handler
            Vector2 pos = node.getPosition();
            if (vector3.x >= pos.x - range && vector3.x <= pos.x + range && vector3.y >= pos.y - range && vector3.y <= pos.y + range) {
                //Gdx.app.log("Indizes:", "current index: " + currentindex + " clicked index: " + i);
                gameplay.movePlayer(localPlayerPawn, node, ticketType);
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


}
