package sy.connection.packages;

public class LobbyPlayerCustomTextureChanged {
    public int connectionId;
    public byte[] customTexture;

    public LobbyPlayerCustomTextureChanged() {
    }

    public LobbyPlayerCustomTextureChanged(int connectionId, byte[] customTexture) {
        this.connectionId = connectionId;
        this.customTexture = customTexture;
    }
}
