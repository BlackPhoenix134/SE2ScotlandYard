package sy.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.lang.reflect.Field;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.List;

import sun.reflect.Reflection;
import sy.core.Annotations.AssetDescriptions;

public final  class SYAssetManager {
    private static AssetManager assetManager = new AssetManager();
    public static Texture solid1x1;

    public static void loadAssets() {
        Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        solid1x1 = new Texture(pixmap);
        Field[] declaredFields = AssetDescriptors.class.getDeclaredFields();
        for (Field field : declaredFields) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                try {
                    assetManager.load((AssetDescriptor<Texture>)field.get(null));
                } catch (IllegalAccessException _) { }
            }
        }
        assetManager.finishLoading();
        pixmap.dispose();
    }

    public static <T> T getAsset(AssetDescriptor<T> assetDescriptor)  {
        if(!assetManager.isLoaded(assetDescriptor))
            assetManager.load(assetDescriptor);
        assetManager.finishLoading();
        return assetManager.get(assetDescriptor);
    }

    public static void dispose() {
        assetManager.dispose();
        solid1x1.dispose();
    }
}
