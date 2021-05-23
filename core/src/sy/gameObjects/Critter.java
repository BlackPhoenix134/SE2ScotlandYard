package sy.gameObjects;


import com.badlogic.gdx.graphics.g2d.Sprite;


import sy.core.LivingBoard.StateMachines.StateMachine;
import sy.rendering.RenderPipeline;

public class Critter extends GameObject {
    private StateMachine stateMachine;
    private Sprite sprite;

    public Critter(String uuid){
        super(uuid);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }

    public void setStateMachine(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void update(float delta) {
        stateMachine.update(delta);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        stateMachine.draw(delta);
        pipeline.add(sprite, 1000);
    }
}
