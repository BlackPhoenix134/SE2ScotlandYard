package sy.assets;

import com.badlogic.gdx.assets.AssetManager;


public class SYAssetManager {


    private static AssetManager assetManager = new AssetManager();

    private SYAssetManager() {
    }

    public static void loadAssets() {
        assetManager.load(AssetDescriptors.BUTTON_DEVIL);
        assetManager.load(AssetDescriptors.GAME_BOARD);
        assetManager.finishLoading();

    }

    public static AssetManager getAssetManager() {
        return assetManager;
    }

    public static void dispose() {
        assetManager.dispose();
    }
}
