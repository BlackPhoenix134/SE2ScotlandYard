package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;

@NetworkPackage
public class AddPawnObject {

    public int netID;
    public int nodeID;
    public boolean isMisterX;

    public AddPawnObject() {

    }

    public AddPawnObject(int netID, int nodeID, boolean isMisterX){
        this.netID = netID;
        this.nodeID = nodeID;
        this.isMisterX = isMisterX;
    }
}
