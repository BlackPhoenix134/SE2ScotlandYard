package sy.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import sy.core.Annotations.AssetDescriptions;

@AssetDescriptions
public final class AssetDescriptors {
    private static final String UI = "ui";
    private static final String WORLD = "startscreen";
    private static final String CHARACTERS = WORLD + "/characters";

    public static final AssetDescriptor<Texture> BUTTON_DEVIL = new AssetDescriptor<>(UI + "/StartDevil.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_OPTIONS = new AssetDescriptor<>(UI + "/OptionsButton.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_EXIT = new AssetDescriptor<>("/ExitDevilButton.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_JOIN = new AssetDescriptor<>("/JoinButton.png", Texture.class);

    public static final AssetDescriptor<Texture> BUTTON_GAMEJOIN = new AssetDescriptor<Texture>(UI + "/JoinGame.png", Texture.class);

    public static final AssetDescriptor<Texture> GAME_BOARD = new AssetDescriptor<>( "/map.png", Texture.class);

    public static final AssetDescriptor<Texture> MONSTER1 = new AssetDescriptor<>(CHARACTERS + "/monster1.png", Texture.class);
    public static final AssetDescriptor<Texture> MONSTER2 = new AssetDescriptor<>(CHARACTERS + "/monster2.png", Texture.class);
    public static final AssetDescriptor<Texture> MONSTER3 = new AssetDescriptor<>(CHARACTERS + "/monster3.png", Texture.class);
    public static final AssetDescriptor<Texture> GHOST_WALKING = new AssetDescriptor<>( CHARACTERS + "/ghostWalking.png", Texture.class);
    public static final AssetDescriptor<Texture> SPIDER_WALKING = new AssetDescriptor<>(CHARACTERS + "/spiderWalking.png", Texture.class);
}
