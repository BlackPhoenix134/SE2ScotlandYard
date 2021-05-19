package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;

@NetworkPackage
public class SpawnObject {
    public Class objClass;
    public int netId;

    public SpawnObject(Class objClass, int netId) {
        this.objClass = objClass;
        this.netId = netId;
    }
}
