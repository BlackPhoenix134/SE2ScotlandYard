package sy;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.gameObjects.DebugObject;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NodeGraphObject;
import sy.input.InputHandler;
import sy.rendering.RenderPipeline;
import sy.screens.GameScreen;
import sy.screens.ScreenEnum;
import sy.screens.ScreenManager;
import sy.screens.TitleScreen;


public class Game extends com.badlogic.gdx.Game {



    @Override
    public void create() {
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showScreen( ScreenEnum.MAIN_MENU);
    }

    @Override
    public void dispose() {

    }
}
