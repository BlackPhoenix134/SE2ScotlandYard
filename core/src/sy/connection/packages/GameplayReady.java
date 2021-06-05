package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;

@NetworkPackage
public class GameplayReady {
    public int id;
    public GameplayReady(){}
    public GameplayReady(int id){
        this.id = id;
    }
}
