package sy.connection.packages;

public class LobbyPlayerCustomTextureChangedRequest {
    public int connectionId;
    public byte[] customTexture;

    public LobbyPlayerCustomTextureChangedRequest() {
    }

    public LobbyPlayerCustomTextureChangedRequest(int connectionId, byte[] customTexture) {
        this.connectionId = connectionId;
        this.customTexture = customTexture;
    }
}
