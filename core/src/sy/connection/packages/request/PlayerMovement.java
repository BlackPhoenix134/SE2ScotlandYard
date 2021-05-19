package sy.connection.packages.request;

import com.badlogic.gdx.math.Vector2;

import sy.core.Annotations.NetworkPackage;


@NetworkPackage
public class PlayerMovement {
    private Vector2 originPosition;
    private Vector2 targetPosition;
    private int playerID;

    public PlayerMovement(int playerID, Vector2 originPosition, Vector2 targetPosition) {
        this.playerID = playerID;
        this.originPosition = originPosition;
        this.targetPosition = targetPosition;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public Vector2 getOriginPosition() {
        return originPosition;
    }

    public void setOriginPosition(Vector2 originPosition) {
        this.originPosition = originPosition;
    }

    public Vector2 getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Vector2 targetPosition) {
        this.targetPosition = targetPosition;
    }
}
