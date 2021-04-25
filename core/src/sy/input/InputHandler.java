package sy.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;

public class InputHandler {

    public InputHandler(){
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new CustomInputListener());
        multiplexer.addProcessor(new GestureDetector(new CustomInputListener()));
        Gdx.input.setInputProcessor(multiplexer);
    }
}
