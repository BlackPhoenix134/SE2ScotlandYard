package sy.core;

import com.esotericsoftware.kryonet.Connection;

public class PlayerTurn {
    private int index;

    public PlayerTurn(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
