package sy.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

public class AssetDescriptors {
    //startscreen
    public static final AssetDescriptor<Texture> BUTTON_DEVIL = new AssetDescriptor<>(Assets.DEVILBUTTON, Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_OPTIONS = new AssetDescriptor<>(Assets.OptionsDevilButton, Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_EXIT = new AssetDescriptor<>(Assets.ExitDevilButton, Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_JOIN = new AssetDescriptor<>(Assets.JoinButton, Texture.class);


    //gameLobby
    public static final AssetDescriptor<Texture> BUTTON_GAMEJOIN = new AssetDescriptor<Texture>(Assets.JoinGameButton, Texture.class);


    //gameboard
    public static final AssetDescriptor<Texture> GAME_BOARD = new AssetDescriptor<>(Assets.GAMEBOARD, Texture.class);

    //player
    public static final AssetDescriptor<Texture> MONSTER1 = new AssetDescriptor<>(Assets.MONSTER1, Texture.class);
    public static final AssetDescriptor<Texture> MONSTER2 = new AssetDescriptor<>(Assets.MONSTER2, Texture.class);
    public static final AssetDescriptor<Texture> MONSTER3 = new AssetDescriptor<>(Assets.MONSTER3, Texture.class);

    //critter
    public static final AssetDescriptor<Texture> GHOST_WALKING = new AssetDescriptor<>(Assets.GHOST1_WALKING, Texture.class);

}
