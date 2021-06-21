package sy.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.core.Clickable;
import sy.core.Consumer;
import sy.core.Math.GoodMath;
import sy.core.Physics.BoundingBox;
import sy.core.clickHandling.ObjectClickBinding;
import sy.core.clickHandling.ObjectClickHandler;
import sy.core.Physics.Area2D;
import sy.core.Physics.Rectangle;
import sy.core.Tickets.TicketType;
import sy.gameObjects.GameObject;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.SpriteDrawableObject;
import sy.input.prio.InputEvent;

public class TicketSelectDialog {
    private List<GameObject> objects = new ArrayList<>();
    private List<ObjectClickBinding> bindings = new ArrayList<>();
    private GameObjectManager gameObjectManager;
    private ObjectClickHandler objectClickHandler;
    private Consumer<TicketType> onOptionChosen;
    private int dialogDrawOrder = 1000;
    private int dialogClickPriority = 1000;

    public TicketSelectDialog(GameObjectManager gameObjectManager, ObjectClickHandler objectClickHandler, List<TicketType> ticketTypes, Consumer<TicketType> onOptionChosen) {
        this.gameObjectManager = gameObjectManager;
        this.objectClickHandler = objectClickHandler;
        this.onOptionChosen = onOptionChosen;
        createDeselectArea();
        Rectangle boundingBox = createTicketButtons(ticketTypes);
        createBackground(boundingBox);

    }

    private void createDeselectArea() {
        bindings.add(objectClickHandler.addTouchUpClickable(new Clickable() {
            @Override
            public void onClicked(InputEvent inputEvent) {
                inputEvent.setConsumed(true);
                invokeClicked(null);
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

    private SpriteDrawableObject createBackground(Rectangle boundingBox) {
        SpriteDrawableObject dialogBackground = gameObjectManager.create(SpriteDrawableObject.class);
        objects.add(dialogBackground);
        Vector2 windowSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        dialogBackground.setDrawOrder(dialogDrawOrder);
        dialogBackground.setUiObject(true);
        Sprite sprite = new Sprite(SYAssetManager.getAsset(AssetDescriptors.WHITE_SQUARE));
        sprite.setColor(new Color(1, 1, 1, 0.6f));
        sprite.setPosition(windowSize.x / 2f, windowSize.y / 2f);
        sprite.setScale(GoodMath.ratio(sprite.getWidth(), windowSize.x - 300), GoodMath.ratio(sprite.getHeight(), windowSize.y - 100));
        dialogBackground.setSprite(sprite);
        bindings.add(objectClickHandler.addTouchUpClickable(new Clickable() {
            @Override
            public void onClicked(InputEvent inputEvent) {
                inputEvent.setConsumed(true);
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

    private Rectangle createTicketButtons(List<TicketType> ticketTypes) {
       Rectangle boundingBox = new Rectangle(0,0,0,0);

        for(int i = 0; i < ticketTypes.size(); i++) {
            TicketType ticketType = ticketTypes.get(i);
            SpriteDrawableObject btn = gameObjectManager.create(SpriteDrawableObject.class);
            objects.add(btn);
            btn.setDrawOrder(dialogDrawOrder + 1);
            btn.setUiObject(true);
            Sprite sprite = new Sprite(SYAssetManager.getAsset(TicketType.toAsset(ticketType)));
            Vector2 buttonPos = calcButtonPosition(sprite, i, ticketTypes.size());
            sprite.setPosition(buttonPos.x, buttonPos.y);
            btn.setSprite(sprite);
            addButtonClickHandler(sprite, ticketType);
        }
        return boundingBox;
    }

    private Vector2 calcButtonPosition(Sprite sprite, int i, int maxI)
    {
        float offsetX = 20;
        float offsetY = 30;
        float directionMult = i % 2 == 0 ? 1 : -1;
        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;
        float width = sprite.getWidth() * sprite.getScaleX() + offsetX;
        float height = sprite.getHeight() * sprite.getScaleY();
        centerY += height / 2  * maxI/2f;
        float x = centerX - directionMult * width / 2;
        float y = centerY - sprite.getHeight() * i/2 - offsetY * i/2;
        return new Vector2(x, y);
    }

    private void addButtonClickHandler(Sprite sprite, TicketType ticketType) {
        bindings.add(objectClickHandler.addTouchUpClickable(new Clickable() {
            @Override
            public void onClicked(InputEvent inputEvent) {
                inputEvent.setConsumed(true);
                invokeClicked(ticketType);
            }

            @Override
            public Vector2 getPosition() {
                return new Vector2(sprite.getX(), sprite.getY());
            }

            @Override
            public Area2D getArea2D() {
               float scaledWidth = sprite.getWidth() * sprite.getScaleX();
                float scaledHeight = sprite.getHeight() * sprite.getScaleY();
                return new BoundingBox(getPosition().x, getPosition().y, scaledWidth, scaledHeight);
            }
        }, dialogClickPriority+2, true));
    }

    private void invokeClicked(TicketType type) {
        hide();
        onOptionChosen.call(type);
    }

    private void hide() {
        for (ObjectClickBinding binding : bindings)
            binding.unsubscribe();
        for (GameObject gameObject : objects)
            gameObject.setAlive(false);
    }
}
