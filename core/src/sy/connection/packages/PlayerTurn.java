package sy.connection.packages;

import com.esotericsoftware.kryonet.Connection;

import sy.core.Annotations.NetworkPackage;

@NetworkPackage
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
