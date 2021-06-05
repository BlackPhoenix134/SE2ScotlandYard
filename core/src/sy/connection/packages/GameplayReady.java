package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;

@NetworkPackage
public class GameplayReady {
    public int playerNetID;
    public GameplayReady(){}
    public GameplayReady(int playerNetID){
        this.playerNetID = playerNetID;
    }
}
