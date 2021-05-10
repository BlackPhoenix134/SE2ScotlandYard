package sy.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

public class AssetDescriptors {
    //startscreen
    public static final AssetDescriptor<Texture> BUTTON_DEVIL = new AssetDescriptor<>(Assets.DEVILBUTTON, Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_OPTIONS = new AssetDescriptor<>(Assets.OptionsDevilButton, Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_EXIT = new AssetDescriptor<>(Assets.ExitDevilButton, Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_JOIN = new AssetDescriptor<>(Assets.JoinButton, Texture.class);


    //gameboard
    public static final AssetDescriptor<Texture> GAME_BOARD = new AssetDescriptor<>(Assets.GAMEBOARD, Texture.class);

    //player
    public static final AssetDescriptor<Texture> PLAYER_1 = new AssetDescriptor<>(Assets.PLAYER1, Texture.class);
}
