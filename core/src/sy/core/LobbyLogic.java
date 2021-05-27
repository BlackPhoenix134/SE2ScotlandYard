package sy.core;

import java.util.HashMap;
import java.util.Map;

import sy.connection.NetworkPackageCallbacks;

public class LobbyLogic {
    protected NetworkPackageCallbacks callbacks;
    protected Map<Integer, Player> currentPlayers = new HashMap<>();
}
