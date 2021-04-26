package sy.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import sy.Game;
import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.input.InputHandler;

public class TitleScreen extends AbstractScreen {
    Game game;
    float w,h,tw,th =0;
    OrthographicCamera camera;
    SpriteBatch batch;
    Sprite img;


    public TitleScreen() {
    }

    @Override
    public void buildStage() {
        this.game = game;
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.position.set(w/2, h/2, 0);
        camera.update();
        batch = new SpriteBatch();
        img = new Sprite(new Texture(Gdx.files.internal("/startscreen/StartDevil.png")));

        tw = img.getWidth();
        th = img.getHeight();
        img.setBounds( camera.position.x - (tw/2), camera.position.y - (th/2),tw,th);
        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                if(img.getBoundingRectangle().contains(screenX, screenY)) {
                    ScreenManager.getInstance().showScreen( ScreenEnum.GAME);

                }

                return true;
            }

        });

    }


    public void show(){
      /*  Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new GameScreen(game));
                }
                return true;
            }
        });*/


    }

    @Override
    public void render(float delta) {
       // Gdx.gl.glClearColor(0, .25f, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*game.batch.begin();
        game.font.draw(game.batch, "Title Screen!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.batch, "Click the circle to win.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "Press space to play.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
        game.batch.end();*/

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        img.draw(batch);
        batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
