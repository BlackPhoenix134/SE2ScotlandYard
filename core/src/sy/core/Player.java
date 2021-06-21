package sy.core;

public class Player {
    private int connectionId;
    private boolean isLocalPlayer;
    private byte[] customTexture;

    public Player(int connectionId){
        this.connectionId = connectionId;
    }

    public byte[] getCustomTexture() {
        return customTexture;
    }

    public Player(int connectionId, byte[] customTexture){
        this.connectionId = connectionId;
        this.customTexture = customTexture;
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
