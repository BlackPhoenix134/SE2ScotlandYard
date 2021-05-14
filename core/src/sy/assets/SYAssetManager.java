package sy.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.AbstractSet;

public class SYAssetManager {
    private static AssetManager assetManager = new AssetManager();
    public static Texture solid1x1;

    private SYAssetManager() {
    }

    public static void loadAssets() {
        Pixmap pixmap = new Pixmap(1,1,Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        solid1x1 = new Texture(pixmap);
        assetManager.load(AssetDescriptors.BUTTON_DEVIL);
        assetManager.load(AssetDescriptors.GAME_BOARD);
        assetManager.load(AssetDescriptors.BUTTON_EXIT);
        assetManager.load(AssetDescriptors.BUTTON_OPTIONS);
        assetManager.load(AssetDescriptors.BUTTON_JOIN);
        assetManager.load(AssetDescriptors.MONSTER1);
        assetManager.load(AssetDescriptors.BUTTON_GAMEJOIN);
        assetManager.finishLoading();

        pixmap.dispose();
    }

    public static <T> T getAsset(AssetDescriptor<T> assetDescriptor)  {
        if(!assetManager.containsAsset(assetDescriptor))
            assetManager.load(assetDescriptor);
        return assetManager.get(assetDescriptor);
    }

    public static void dispose() {
        assetManager.dispose();
        solid1x1.dispose();
    }
}
