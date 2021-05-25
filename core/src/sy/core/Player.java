package sy.core;

import com.esotericsoftware.kryonet.Connection;

public class Player {
    private Connection index;
    public Player(Connection index){
        this.index = index;
    }
    public Connection getIndex() {
        return index;
    }
}
