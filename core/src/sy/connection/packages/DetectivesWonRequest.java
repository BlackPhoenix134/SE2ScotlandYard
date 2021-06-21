package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;

@NetworkPackage
public class DetectivesWonRequest {
    public int netID;

    public DetectivesWonRequest() {
    }

    public DetectivesWonRequest(int netID) {
        this.netID = netID;
    }
}
