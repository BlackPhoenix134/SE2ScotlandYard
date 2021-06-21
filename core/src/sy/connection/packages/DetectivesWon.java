package sy.connection.packages;

import sy.core.Annotations.NetworkPackage;

@NetworkPackage
public class DetectivesWon {

    public int playerID;

    public DetectivesWon() {
    }

    public DetectivesWon(int playerID) {
        this.playerID = playerID;
    }
}
