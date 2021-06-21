package sy.connection.packages;

public class CreateLobbyPlayer {
    public int connectionId;
    public byte[] customTexture = null;

    public CreateLobbyPlayer() {
    }

    public CreateLobbyPlayer(int connectionId, byte[] customTexture) {
        this.connectionId = connectionId;
        this.customTexture = customTexture;
    }
}
