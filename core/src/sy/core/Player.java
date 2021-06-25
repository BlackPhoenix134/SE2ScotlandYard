package sy.core;

public class Player {
    private int connectionId;
    private boolean isLocalPlayer;
    private byte[] customTexture;
    private String name;

    public Player(int connectionId, String name){
        this.connectionId = connectionId;
        this.name = name;
    }

    public byte[] getCustomTexture() {
        return customTexture;
    }

    public Player(int connectionId, byte[] customTexture, String name){
       this(connectionId, name);
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

    public String getName() {
        return name;
    }
}
