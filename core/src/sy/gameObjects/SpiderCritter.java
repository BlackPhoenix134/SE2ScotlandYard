package sy.gameObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.core.LivingBoard.PathNode;
import sy.core.LivingBoard.SpiderStates.SpiderStates;
import sy.core.LivingBoard.SpiderStates.StateIdle;
import sy.core.LivingBoard.SpiderStates.StateWander;
import sy.core.LivingBoard.StateMachine;
import sy.core.Visuals.AnimationController;
import sy.rendering.RenderPipeline;

public class SpiderCritter extends GameObject {
    private AnimationController animationController;
    private Sprite sprite;
    private StateMachine<SpiderCritter> stateMachine;
    private List<Vector2> wanderPoints = new ArrayList<Vector2>();

    public SpiderCritter(String uuid){
        super(uuid);
        this.animationController = new AnimationController(SYAssetManager.getAsset(AssetDescriptors.SPIDER_WALKING), 11, 1, 0.2f);
        this.stateMachine = new StateMachine<>(this);
        stateMachine.addState(SpiderStates.IDLE.ordinal(), new StateIdle(this.stateMachine));
        stateMachine.addState(SpiderStates.WANDER.ordinal(), new StateWander(this.stateMachine));
        sprite = new Sprite(animationController.getCurrentFrame());
    }

    public void setWanderPoints(List<Vector2> wanderPoints) {
        this.wanderPoints = wanderPoints;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public AnimationController getAnimationController() {
        return animationController;
    }

    @Override
    public void update(float delta) {
        stateMachine.update(delta);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        stateMachine.draw(delta);
        pipeline.add(sprite, 5);
    }


}
