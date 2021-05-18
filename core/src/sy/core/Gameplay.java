package sy.core;

import sy.gameObjects.PlayerObject;

public abstract class Gameplay {
    private Player player;
    private int playerTurnId;

    public Gameplay(Player player) {
        this.player = player;
    }

    public boolean isLocalTurn() {
        return playerTurnId == player.getId();
    }

    public boolean canMove(PlayerObject playerObject, MapNode toNode) {
        //do checks, used in client and server so implemented here
        return true;
    }

    public abstract void movePlayer(PlayerObject playerObject, MapNode toNode);
}
