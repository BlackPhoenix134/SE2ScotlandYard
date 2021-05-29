package sy.core;

public class Player {
    private int connectionId;

    public Player(int connectionId){
        this.connectionId = connectionId;
    }

    public int getConnectionId() {
        return connectionId;
    }
}
