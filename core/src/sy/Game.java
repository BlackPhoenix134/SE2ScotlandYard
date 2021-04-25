package sy;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.gameObjects.DebugObject;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NodeGraphObject;
import sy.input.InputHandler;
import sy.rendering.RenderPipeline;


public class Game extends ApplicationAdapter {
    private final float TICKS = 1f / 60f;
    private float tickAccumulation = 0;


    private GameObjectManager gameObjectManager = new GameObjectManager();
    private RenderPipeline renderPipeline;
    private OrthographicCamera camera;
    private ExtendViewport viewport;

    private InputHandler inputHandler;
    private Vector2 oldDragValue = new Vector2();

    private World world = new World(new Vector2(0, 0), true);

    public Game() {

    }

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 600, camera);
        renderPipeline = new RenderPipeline(new SpriteBatch(), camera, viewport);
        DebugObject obj = gameObjectManager.create(DebugObject.class);
        gameObjectManager.create(NodeGraphObject.class);
        SYAssetManager.loadAssets();
        Texture buttonDevil = SYAssetManager.getAssetManager().get(AssetDescriptors.BUTTON_DEVIL);
        obj.setTexture(buttonDevil);
        inputHandler = new InputHandler();
    }


    private void stepTick(float delta) {
        gameObjectManager.update(delta);
        gameObjectManager.postUpdate();

    }

    private void stepFastUpdate(float delta) {
        renderPipeline.getDefaultRenderer().begin();
        gameObjectManager.draw(delta, renderPipeline);
        renderPipeline.getDefaultRenderer().end();
        updateCam();
        renderPipeline.updateBatchMatrix();
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tickAccumulation += Math.min(delta, 0.25f);
        if (tickAccumulation >= TICKS) {
            tickAccumulation -= TICKS; //takes multi phys misses into account (low fps)
            stepTick(delta);
        }
        stepFastUpdate(delta);

    }

    //Needs refactoring in other class...
    private void updateCam(){
        camera.zoom = inputHandler.getZoomValue();
        Vector2 drag = inputHandler.getDragValue();
        if(oldDragValue.x != drag.x || oldDragValue.y != drag.y)
        {
            camera.position.add(-drag.x, drag.y, 0);
            oldDragValue.x = drag.x;
            oldDragValue.y = drag.y;
        }
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        renderPipeline.updateBatchMatrix();
    }

    @Override
    public void dispose() {
        renderPipeline.dispose();
        world.dispose();
        SYAssetManager.dispose();
    }
}
