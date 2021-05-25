package sy.core;

import com.esotericsoftware.kryonet.Connection;

public class PlayerTurn {
    private Connection index;

    public PlayerTurn(Connection index) {
        this.index = index;
    }

    public Connection getIndex() {
        return index;
    }

    public void setIndex(Connection index) {
        this.index = index;
    }
}
