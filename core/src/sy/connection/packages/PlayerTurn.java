package sy.connection.packages;

import com.esotericsoftware.kryonet.Connection;

import sy.core.Annotations.NetworkPackage;

@NetworkPackage
public class PlayerTurn {
    public int index;

    public PlayerTurn(){

    }
    public PlayerTurn(int index) {
        this.index = index;
    }
}
