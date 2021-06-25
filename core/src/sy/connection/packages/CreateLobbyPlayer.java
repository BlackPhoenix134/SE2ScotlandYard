package sy.connection.packages;

public class CreateLobbyPlayer {
    public int connectionId;
    public byte[] customTexture = null;
    public String name;

    public CreateLobbyPlayer() {
    }

    public CreateLobbyPlayer(int connectionId, byte[] customTexture, String name) {
        this.connectionId = connectionId;
        this.customTexture = customTexture;
        this.name = name;
    }
}
