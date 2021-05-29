package sy.core;

public class Player {
    private int connectionId;
    private boolean isLocalPlayer;

    public Player(int connectionId){
        this.connectionId = connectionId;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public boolean isLocalPlayer() {
        return isLocalPlayer;
    }

    public void setLocalPlayer(boolean localPlayer) {
        isLocalPlayer = localPlayer;
    }
}
