package sy.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.core.Clickable;
import sy.core.Consumer;
import sy.core.Math.GoodMath;
import sy.core.Physics.Area2D;
import sy.core.Physics.BoundingBox;
import sy.core.Physics.Rectangle;
import sy.core.Tickets.TicketType;
import sy.core.clickHandling.ObjectClickBinding;
import sy.core.clickHandling.ObjectClickHandler;
import sy.gameObjects.GameObject;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.SpriteDrawableObject;
import sy.input.prio.InputEvent;
import sy.rendering.RenderPipeline;

public class MrXTicketsDialog extends GameObject{
    private List<ObjectClickBinding> bindings = new ArrayList<>();
    private GameObjectManager gameObjectManager = new GameObjectManager();
    private ObjectClickHandler objectClickHandler;
    private int dialogDrawOrder = 1000;
    private int dialogClickPriority = 1000;

    MrXTicketsDialog(String uuid) {
        super(uuid);
    }


    public void initialize(ObjectClickHandler objectClickHandler, List<TicketType> ticketTypes) {
        this.objectClickHandler = objectClickHandler;
        createDeselectArea();
        Vector2 windowSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        BoundingBox boundingBox = new BoundingBox(windowSize.x / 2f, windowSize.y / 2f, windowSize.x - 300, windowSize.y - 100);
        createBackground(boundingBox);
        boundingBox.setHeight(boundingBox.getHeight() - 100);
        createTicketsUsed(ticketTypes, boundingBox);
    }

    private void createDeselectArea() {
        bindings.add(objectClickHandler.addTouchUpClickable(new Clickable() {
            @Override
            public void onClicked(InputEvent inputEvent) {
                inputEvent.setConsumed(true);
                hide();
            }

            @Override
            public Vector2 getPosition() {
                return new Vector2(0, 0);
            }

            @Override
            public Area2D getArea2D() {
                return new Rectangle(-10000, -10000, 20000, 20000);
            }
        }, dialogClickPriority, true));
    }

    private SpriteDrawableObject createBackground(BoundingBox boundingBox) {
        SpriteDrawableObject dialogBackground = gameObjectManager.create(SpriteDrawableObject.class);

        dialogBackground.setDrawOrder(dialogDrawOrder);
        dialogBackground.setUiObject(true);
        Sprite sprite = new Sprite(SYAssetManager.getAsset(AssetDescriptors.WHITE_SQUARE));
        sprite.setColor(new Color(1, 1, 1, 0.6f));
        sprite.setPosition(boundingBox.getCenterX(), boundingBox.getCenterY());
        sprite.setScale(GoodMath.ratio(sprite.getWidth(), boundingBox.getWidth()), GoodMath.ratio(sprite.getHeight(), boundingBox.getHeight()));
        dialogBackground.setSprite(sprite);
        bindings.add(objectClickHandler.addTouchUpClickable(new Clickable() {
            @Override
            public void onClicked(InputEvent inputEvent) {
                inputEvent.setConsumed(true);
                hide();
            }

            @Override
            public Vector2 getPosition() {
                return new Vector2(sprite.getX(), sprite.getY());
            }

            @Override
            public Area2D getArea2D() {
                float scaledWidth = sprite.getWidth() * sprite.getScaleX();
                float scaledHeight = sprite.getHeight() * sprite.getScaleY();
                return new Rectangle(sprite.getX() - scaledWidth/2, sprite.getY() - scaledHeight/2,
                        scaledWidth, scaledHeight);
            }
        }, dialogClickPriority+1, true));
        return dialogBackground;
    }

    private void createTicketsUsed(List<TicketType> ticketTypes, BoundingBox boundingBox) {
        int idx = 0;
        int maxCols = ((int)ticketTypes.size() / 10); //in indices way (0 start)
        int itemsPerCol = 10;
        int currCol = 0;
        int itemCount = 0;

        List<Sprite> cardSprites = new ArrayList<>();
        while(idx < ticketTypes.size()) {
            TicketType ticketType = ticketTypes.get(idx);
            if(itemCount > itemsPerCol) {
                itemCount = 0;
                currCol++;
            }
            SpriteDrawableObject cardObj = createTicketCard(ticketType, currCol, itemCount, maxCols);
            Sprite cardSprite = cardObj.getSprite();
            cardSprites.add(cardSprite);

            float cardScale = calcCardScale(boundingBox, cardSprite, itemsPerCol);
            cardSprite.setScale(cardScale);

            Vector2 buttonPos = calcButtonPosition(cardSprite, boundingBox, currCol, itemCount);
            cardSprite.setPosition(buttonPos.x, buttonPos.y);

            idx++;
            itemCount++;
        }
        if(cardSprites.size() > 0)
            centerCards(cardSprites, maxCols);
    }

    private void centerCards(List<Sprite> cardSprites, int maxCols) {
        Sprite card0 = cardSprites.get(0);
        maxCols = maxCols / 2;
        float width = card0.getWidth() * card0.getScaleX();
        for(Sprite cardSprite : cardSprites)
            cardSprite.setX(cardSprite.getX() - width * maxCols);
    }

    private SpriteDrawableObject createTicketCard(TicketType ticketType, int col, int itemCount, int maxCols) {
        SpriteDrawableObject obj = gameObjectManager.create(SpriteDrawableObject.class);
        obj.setDrawOrder(dialogDrawOrder + 1);
        obj.setUiObject(true);
        Sprite sprite = new Sprite(SYAssetManager.getAsset(TicketType.toAsset(ticketType)));
        obj.setSprite(sprite);
        return obj;
    }

    private float calcCardScale(BoundingBox boundingBox, Sprite sprite, int itemsPerCol) {
        return GoodMath.ratio(sprite.getHeight(), (boundingBox.getHeight() - 100) / itemsPerCol);
    }

    private Vector2 calcButtonPosition(Sprite sprite, BoundingBox boundingBox, int col, int itemCount)
    {
        float sWidth = sprite.getWidth() * sprite.getScaleX();
        float sHeight = sprite.getHeight() * sprite.getScaleY();
        float sHeightHalf = sHeight / 2;
        float start = boundingBox.getCenterY() + boundingBox.getUp() - sHeightHalf;
       float x = boundingBox.getCenterX() + sWidth * col;
        float y = start - sHeight * itemCount;
        return new Vector2(x, y);
    }

    private void hide() {
        for (ObjectClickBinding binding : bindings)
            binding.unsubscribe();
        setAlive(false);
    }

    @Override
    public void update(float delta) {
        gameObjectManager.update(delta);
    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        gameObjectManager.draw(delta, pipeline);
    }
}
