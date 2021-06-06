package sy.core;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import sy.assets.AssetDescriptors;
import sy.assets.SYAssetManager;
import sy.connection.NetworkPackageCallbacks;
import sy.connection.packages.*;
import sy.core.Tickets.DetectiveTickets;
import sy.core.Tickets.MisterXTickets;
import sy.gameObjects.GameObjectManager;
import sy.gameObjects.NodeGraphObject;
import sy.gameObjects.PawnDetectiveObject;
import sy.gameObjects.PawnMisterXObject;
import sy.gameObjects.PawnObject;

public abstract class Gameplay {
    protected Player player;
    private int playerTurnId;
    protected NodeGraphObject nodeGraphObject;
    protected NetworkPackageCallbacks callbacks;
    protected List<Player> players;
    protected GameObjectManager gameObjectManager;
    protected PawnMisterXObject pawnMisterXObject;
    protected List<PawnDetectiveObject> pawnDetectiveObjectList = new ArrayList<>();
    protected PawnObject playerPawnObject;
    protected int gameround = 0;


    public Gameplay(Player player, List<Player> players, NetworkPackageCallbacks callbacks, GameObjectManager gameObjectManager) {
        this.player = player;
        this.players = players;
        this.callbacks = callbacks;
        this.gameObjectManager = gameObjectManager;
        registerCallbacks();
    }

    public abstract void initialize(NodeGraphObject nodeGraphObject);

    private void registerCallbacks() {

        callbacks.registerCallback(MovePlayerObject.class, packageObj -> {
            List<PawnObject> pawnObjectList = getPawnObjects();
            MovePlayerObject playerMoved = (MovePlayerObject) packageObj;
            for (PawnObject player : pawnObjectList) {
                if (player.getNetId() == playerMoved.playerObjectNetId) {
                    MapNode newMapNode = nodeGraphObject.getMapNodes().get(playerMoved.newNodeId);
                    player.setMapNode(newMapNode);
                    break;
                }
            }
        });

        callbacks.registerCallback(DetectiveDies.class, packageObj ->{
            DetectiveDies detectiveDies = (DetectiveDies) packageObj;
            for (PawnDetectiveObject pawnDetectiveObject: pawnDetectiveObjectList){
                if (pawnDetectiveObject.getNetId() == detectiveDies.netID){
                    pawnDetectiveObject.setAlive(false);
                    pawnDetectiveObjectList.remove(pawnDetectiveObject);
                    break;
                }
            }
        });

        callbacks.registerCallback(DetectivesWon.class, packageObj -> {
            DetectivesWon detectivesWon = (DetectivesWon) packageObj;
            Gdx.app.log("Winner: ", "The detectives won");
            //TODO: Show new screen
        });
    }

    public List<PawnObject> getPawnObjects() {
        List<PawnObject> pawnObjectList = new ArrayList<>();
        pawnObjectList.addAll(pawnDetectiveObjectList);
        pawnObjectList.add(pawnMisterXObject);
        return pawnObjectList;
    }

    public int getPlayerTurnId() {
        return playerTurnId;
    }

    public void setPlayerTurnId(int playerTurnId) {
        this.playerTurnId = playerTurnId;
    }

    public boolean isLocalTurn() {
        return playerTurnId == player.getConnectionId();
    }

    public abstract boolean canMove(MapNode toNode, sy.core.Tickets.TicketType ticketType);

    public abstract void movePlayer(MapNode toNode, sy.core.Tickets.TicketType ticketType);

}
