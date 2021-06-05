package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;

@NetworkPackage
public class PlayerTurn {
    public int id;

    public PlayerTurn(){

    }
    public PlayerTurn(int id) {
        this.id = id;
    }
}
