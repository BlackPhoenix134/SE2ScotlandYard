package sy.connection.packages;

public class LobbyPlayerReadySync {
    public int connectionId;
    public boolean isReady;

    public LobbyPlayerReadySync() {

    }

    public LobbyPlayerReadySync(int connectionId, boolean isReady) {
        this.connectionId = connectionId;
        this.isReady = isReady;

    }
}
