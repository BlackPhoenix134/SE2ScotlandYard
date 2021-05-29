package sy.assets;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import sy.core.Annotations.AssetDescriptions;

@AssetDescriptions
public final class AssetDescriptors {
    private static final String UI = "ui";
    private static final String WORLD = "world";
    private static final String CHARACTERS = WORLD + "/characters";
    private static final String TICKETS = WORLD + "/tickets";

    public static final AssetDescriptor<Texture> BUTTON_DEVIL = new AssetDescriptor<>(UI + "/StartDevil.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_EXIT = new AssetDescriptor<>(UI + "/ExitDevilButton.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_JOIN = new AssetDescriptor<>(UI + "/JoinButton.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_GAMEJOIN = new AssetDescriptor<>(UI + "/JoinGame.png", Texture.class);
    public static final AssetDescriptor<Texture> HOST_GAME = new AssetDescriptor<>(UI + "/HostGame.png", Texture.class);
    public static final AssetDescriptor<Texture> LEAVE = new AssetDescriptor<>(UI + "/exit.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_READY = new AssetDescriptor<>(UI + "/ReadyButton.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_START = new AssetDescriptor<>(UI + "/Start.png", Texture.class);



    public static final AssetDescriptor<Texture> GAME_BOARD = new AssetDescriptor<>( WORLD + "/map.png", Texture.class);

    public static final AssetDescriptor<Texture> MONSTER1 = new AssetDescriptor<>(CHARACTERS + "/monster1.png", Texture.class);
    public static final AssetDescriptor<Texture> MONSTER2 = new AssetDescriptor<>(CHARACTERS + "/monster2.png", Texture.class);
    public static final AssetDescriptor<Texture> MONSTER3 = new AssetDescriptor<>(CHARACTERS + "/monster3.png", Texture.class);
    public static final AssetDescriptor<Texture> GHOST_WALKING = new AssetDescriptor<>( CHARACTERS + "/ghostWalking.png", Texture.class);
    public static final AssetDescriptor<Texture> SPIDER_WALKING = new AssetDescriptor<>(CHARACTERS + "/spiderWalking.png", Texture.class);

    public static final AssetDescriptor<Texture> BIKE = new AssetDescriptor<> (TICKETS + "/bike.png", Texture.class);
    public static final AssetDescriptor<Texture> BLACK_TICKET = new AssetDescriptor<> (TICKETS + "/blackticket.png", Texture.class);
    public static final AssetDescriptor<Texture> DOUBLE_TICKET = new AssetDescriptor<> (TICKETS + "/doubleticket.png", Texture.class);
    public static final AssetDescriptor<Texture> DRAGON = new AssetDescriptor<> (TICKETS + "/dragon.png", Texture.class);
    public static final AssetDescriptor<Texture> HORSE = new AssetDescriptor<> (TICKETS + "/horse.png", Texture.class);

    public static final AssetDescriptor<Texture> BUTTON_MISTERX = new AssetDescriptor<>(UI + "/btnMisterX.png", Texture.class);
    public static final AssetDescriptor<Texture> BUTTON_DETECTIVE = new AssetDescriptor<>(UI + "/btnDetective.png", Texture.class);
}
