package sy.core;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;

import java.io.OutputStream;

public class Player {
    private int connectionId;
    private boolean isLocalPlayer;
    private byte[] customTexture;

    public Player(int connectionId){
        this.connectionId = connectionId;
    }

    public Texture getCustomTexture() {
        return new Texture(new Pixmap(customTexture, 0, customTexture.length));
    }
    public Player(int connectionId, byte[] customTexture){
        this.connectionId = connectionId;
        this.customTexture = customTexture;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public boolean isLocalPlayer() {
        return isLocalPlayer;
    }

    public void setLocalPlayer(boolean localPlayer) {
        isLocalPlayer = localPlayer;
    }
}
