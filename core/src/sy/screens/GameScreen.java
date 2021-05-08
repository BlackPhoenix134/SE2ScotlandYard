package sy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.gameObjects.GameBoardObject;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NodeGraphObject;
import sy.input.InputHandler;
import sy.input.PanListener;
import sy.input.TouchDownListener;
import sy.input.TouchUpListener;
import sy.input.ZoomListener;
import sy.rendering.RenderPipeline;

public class GameScreen extends AbstractScreen implements TouchDownListener, TouchUpListener, ZoomListener, PanListener {
    private final float TICKS = 1f / 60f;
    private float tickAccumulation = 0;
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private RenderPipeline renderPipeline;
    private OrthographicCamera camera;
    private ScreenManager screenManager;
    public NodeGraphObject nodeGraphObject;
    private InputHandler inputHandler;
    private Vector2 dragValue = new Vector2();
    private Vector2 oldDragValue = new Vector2();
    private float currentScale = 1;
    private float zoomValue = 1;

    private World world = new World(new Vector2(0, 0), true);

    public GameScreen(RenderPipeline renderPipeline, OrthographicCamera camera, ScreenManager screenManager) {
        this.renderPipeline = renderPipeline;
        this.camera = camera;
        this.screenManager = screenManager;

        //gameObjectManager.create(NodeGraphObject.class);

        GameBoardObject gameBoardObject = gameObjectManager.create(GameBoardObject.class);
        Texture gameBoardTexture = SYAssetManager.getAssetManager().get(AssetDescriptors.GAME_BOARD);
        gameBoardObject.setTexture(gameBoardTexture);


        nodeGraphObject = new NodeGraphObject("hi");

    }


    @Override
    public void buildStage() {

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
        gameObjectManager.update(delta);
        gameObjectManager.postUpdate();
    }

    private void stepFastUpdate(float delta) {
        renderPipeline.getDefaultRenderer().begin();
        gameObjectManager.draw(delta, renderPipeline);
        nodeGraphObject.draw(delta, renderPipeline);
        renderPipeline.getDefaultRenderer().end();
        updateCam();
        renderPipeline.updateBatchMatrix();
    }


    @Override
    public void show() {
        setUpInputHandler();
    }

    private void setUpInputHandler(){
        this.inputHandler = new InputHandler();
        this.inputHandler.setTouchUpListener(this);
        this.inputHandler.setTouchDownListener(this);
        this.inputHandler.setZoomListener(this);
        this.inputHandler.setPanListener(this);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    //Needs refactoring in other class...
    private void updateCam(){
        camera.zoom = this.zoomValue;
        if(oldDragValue.x != dragValue.x || oldDragValue.y != dragValue.y)
        {
            camera.position.add(-dragValue.x, dragValue.y, 0);
            oldDragValue.x = dragValue.x;
            oldDragValue.y = dragValue.y;
        }
        camera.update();
    }

    @Override
    public void dispose() {
        renderPipeline.dispose();
        world.dispose();

    }

    @Override
    public void onTouchUp(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("Game", "TOUCH ON " + screenX + ", " + screenY);
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

    public void removeInputHandlerEvents(){
        inputHandler.setTouchUpListener(null);
        inputHandler.setTouchDownListener(null);
        inputHandler.setZoomListener(null);
        inputHandler.setPanListener(null);
    }
}
