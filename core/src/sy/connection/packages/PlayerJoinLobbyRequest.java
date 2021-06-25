package sy.connection.packages;

public class PlayerJoinLobbyRequest {
    public int connectionId;
    public String name;

    public PlayerJoinLobbyRequest() {
    }

    public PlayerJoinLobbyRequest(int connectionId, String name) {
        this.connectionId = connectionId;
        this.name = name;
    }
}
