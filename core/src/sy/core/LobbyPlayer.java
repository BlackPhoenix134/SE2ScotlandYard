package sy.core;

public class LobbyPlayer {
    private int connectionId;
    private boolean isReady;

    public LobbyPlayer(int connectionId){
        this.connectionId = connectionId;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
