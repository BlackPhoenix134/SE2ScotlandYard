package sy.rendering;

import java.lang.reflect.Constructor;
import java.util.Stack;

public class DrawableItemPools  {
    private Stack<DrawableItemSprite> availableDrawableSprites = new Stack<>();
    private Stack<DrawableItemTexture> availableDrawableTextures = new Stack<>();
    private Stack<DrawableItemText> availableDrawableTexts = new Stack<>();

    public void returnToPool(DrawableItem drawableItem) {

    }

    public DrawableItemSprite getDrawableItemSprite() {
        if(hasAvailable(availableDrawableSprites))
            return availableDrawableSprites.pop();
        else {
            grow(availableDrawableSprites, DrawableItemSprite.class);
            return getDrawableItemSprite();
        }
    }


    public DrawableItemTexture getDrawableItemTexture() {
        if(hasAvailable(availableDrawableTextures))
            return availableDrawableTextures.pop();
        else {
            grow(availableDrawableTextures, DrawableItemTexture.class);
            return getDrawableItemTexture();
        }
    }


    public DrawableItemText getDrawableItemText() {
        if(hasAvailable(availableDrawableTexts))
            return availableDrawableTexts.pop();
        else {
            grow(availableDrawableTexts, DrawableItemText.class);
            return getDrawableItemText();
        }
    }

    private <T extends DrawableItem> boolean hasAvailable(Stack<T> items) {
        return items.size() > 0;
    }

    private <T extends DrawableItem> void grow(Stack<T> items, Class<T> cls)  {
       try {
           Constructor<?> ctor = cls.getConstructor();
           for (int i = 0; i < 25; i++) {
               Object object = ctor.newInstance();
               items.add((T)object);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
